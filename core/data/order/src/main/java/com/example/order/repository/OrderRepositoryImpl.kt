package com.example.order.repository

import com.example.order.datasource.OrderRemoteDataSource
import com.example.order.mapper.toDomain
import com.example.order.mapper.toDto
import com.example.order.model.Order
import com.example.order.model.OrderItem

class OrderRepositoryImpl(
    private val remoteDataSource: OrderRemoteDataSource
) : OrderRepository {

    override suspend fun getOrderById(orderId: Int): Order {
        return remoteDataSource.getOrderById(orderId).toDomain()
    }

    override suspend fun placeOrder(items: List<OrderItem>, paymentMethod: String): Order {
        val dtos = items.map { it.toDto() }
        return remoteDataSource.placeOrder(dtos, paymentMethod).toDomain()
    }
}
