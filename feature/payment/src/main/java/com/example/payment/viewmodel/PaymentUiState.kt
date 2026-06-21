package com.example.payment.viewmodel

enum class PaymentMethod(val displayName: String, val icon: String) {
    CREDIT_CARD("كارت ائتمان", "💳"),
    CASH("كاش عند الاستلام", "💵"),
    DIGITAL_WALLET("محفظة إلكترونية", "📱")
}

sealed interface PaymentUiState {
    data class Idle(
        val orderId: Int,
        val orderItems: List<OrderItemUi>,
        val selectedMethod: PaymentMethod = PaymentMethod.CREDIT_CARD,
        val isProcessing: Boolean = false
    ) : PaymentUiState

    data object Success : PaymentUiState
    data class Error(val message: String) : PaymentUiState
}

data class OrderItemUi(
    val name: String,
    val quantity: Int,
    val price: Double
)
