package com.example.payment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.order.model.OrderItem
import com.example.payment.usecase.GetOrderDetailsUseCase
import com.example.payment.usecase.PlaceOrderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val getOrderDetailsUseCase: GetOrderDetailsUseCase,
    private val placeOrderUseCase: PlaceOrderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PaymentUiState>(
        PaymentUiState.Idle(
            orderId = 0,
            orderItems = emptyList()
        )
    )
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    fun initOrder(orderId: Int) {
        viewModelScope.launch {
            try {
                val order = getOrderDetailsUseCase(orderId)
                val orderItemsUi = order.items.map {
                    OrderItemUi(
                        name = it.name,
                        quantity = it.quantity,
                        price = it.price
                    )
                }
                _uiState.value = PaymentUiState.Idle(
                    orderId = order.id,
                    orderItems = orderItemsUi
                )
            } catch (e: Exception) {
                _uiState.value = PaymentUiState.Error("Failed to load order: ${e.message}")
            }
        }
    }

    fun onPaymentMethodSelected(method: PaymentMethod) {
        val currentState = _uiState.value
        if (currentState is PaymentUiState.Idle) {
            _uiState.update { currentState.copy(selectedMethod = method) }
        }
    }

    fun confirmPayment() {
        val currentState = _uiState.value
        if (currentState is PaymentUiState.Idle) {
            viewModelScope.launch {
                _uiState.update { currentState.copy(isProcessing = true) }
                try {
                    val orderItems = currentState.orderItems.map {
                        OrderItem(
                            name = it.name,
                            quantity = it.quantity,
                            price = it.price
                        )
                    }
                    placeOrderUseCase(orderItems, currentState.selectedMethod.name)
                    _uiState.value = PaymentUiState.Success
                } catch (e: Exception) {
                    _uiState.update { currentState.copy(isProcessing = false) }
                    _uiState.value = PaymentUiState.Error("Payment failed: ${e.message}")
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            getOrderDetailsUseCase: GetOrderDetailsUseCase,
            placeOrderUseCase: PlaceOrderUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                PaymentViewModel(
                    getOrderDetailsUseCase = getOrderDetailsUseCase,
                    placeOrderUseCase = placeOrderUseCase
                )
            }
        }
    }
}