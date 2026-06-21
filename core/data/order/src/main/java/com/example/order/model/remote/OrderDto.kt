package com.example.order.model.remote

data class OrderDto(
    val id: Int,
    val items: List<OrderItemDto>,
    val deliveryFee: Double,
    val status: String
)

data class OrderItemDto(
    val name: String,
    val quantity: Int,
    val price: Double
)
