package com.example.order.model

data class Order(
    val id: Int,
    val items: List<OrderItem>,
    val deliveryFee: Double,
    val status: String
)

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Double
)
