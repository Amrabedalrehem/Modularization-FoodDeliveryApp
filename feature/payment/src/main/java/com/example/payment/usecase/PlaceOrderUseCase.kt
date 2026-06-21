package com.example.payment.usecase

import com.example.order.model.Order
import com.example.order.model.OrderItem
import com.example.order.repository.OrderRepository

class PlaceOrderUseCase(private val repository: OrderRepository) {
    suspend operator fun invoke(items: List<OrderItem>, paymentMethod: String): Order {
        return repository.placeOrder(items, paymentMethod)
    }
}
