package com.example.order.repository

import com.example.order.model.Order
import com.example.order.model.OrderItem

interface OrderRepository {
    suspend fun getOrderById(orderId: Int): Order
    suspend fun placeOrder(items: List<OrderItem>, paymentMethod: String): Order
}
