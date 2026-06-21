package com.example.order.datasource

import com.example.order.model.remote.OrderDto
import com.example.order.model.remote.OrderItemDto

interface OrderRemoteDataSource {
    suspend fun getOrderById(orderId: Int): OrderDto
    suspend fun placeOrder(items: List<OrderItemDto>, paymentMethod: String): OrderDto
}
