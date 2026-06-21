package com.example.payment.usecase

import com.example.order.model.Order
import com.example.order.repository.OrderRepository

class GetOrderDetailsUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(orderId: Int): Order {
        return repository.getOrderById(orderId)
    }
}
