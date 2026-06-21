package com.example.payment.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.theme.Dark900
import com.example.payment.view.component.ConfirmPaymentButton
import com.example.payment.view.component.OrderIdBadge
import com.example.payment.view.component.OrderItemRow
import com.example.payment.view.component.PaymentTopBar
import com.example.payment.view.component.PaymentErrorScreen
import com.example.payment.view.component.PaymentMethodCard
import com.example.payment.view.component.PaymentSuccessScreen
import com.example.payment.view.component.PriceSummaryCard

import com.example.payment.viewmodel.PaymentMethod

import com.example.payment.viewmodel.PaymentUiState
import com.example.payment.viewmodel.PaymentViewModel


@Composable
fun PaymentScreen(
    orderId: Int,
    viewModel: PaymentViewModel,
    onBackClick: () -> Unit = {},
    onPaymentSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(orderId) { viewModel.initOrder(orderId) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark900)
    ) {
        AnimatedContent(
            targetState = uiState,
            transitionSpec = { fadeIn(tween(400)) togetherWith fadeOut(tween(200)) },
            label = "payment_content"
        ) { state ->
            when (state) {
                is PaymentUiState.Idle -> PaymentContent(
                    state = state,
                    onBackClick = onBackClick,
                    onMethodSelected = viewModel::onPaymentMethodSelected,
                    onConfirm = viewModel::confirmPayment
                )

                is PaymentUiState.Success -> PaymentSuccessScreen(onDone = onPaymentSuccess)
                is PaymentUiState.Error -> PaymentErrorScreen(message = state.message)
            }
        }
    }
}


@Composable
fun PaymentContent(
    state: PaymentUiState.Idle,
    onBackClick: () -> Unit,
    onMethodSelected: (PaymentMethod) -> Unit,
    onConfirm: () -> Unit
) {
    val subtotal = state.orderItems.sumOf { it.price * it.quantity }
    val deliveryFee = 15.0
    val total = subtotal + deliveryFee

    Scaffold(
        containerColor = Dark900,
        topBar = { PaymentTopBar(onBackClick = onBackClick) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { OrderIdBadge(orderId = state.orderId) }

            items(state.orderItems) { item -> OrderItemRow(item = item) }

            item { PriceSummaryCard(subtotal = subtotal, deliveryFee = deliveryFee, total = total) }

            items(PaymentMethod.entries.toTypedArray()) { method ->
                PaymentMethodCard(
                    method = method,
                    isSelected = state.selectedMethod == method,
                    onClick = { onMethodSelected(method) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
                ConfirmPaymentButton(
                    total = total,
                    isProcessing = state.isProcessing,
                    onConfirm = onConfirm
                )
            }
        }
    }
}
