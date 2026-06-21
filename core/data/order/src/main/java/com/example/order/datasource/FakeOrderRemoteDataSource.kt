package com.example.order.datasource

import com.example.order.model.remote.OrderDto
import com.example.order.model.remote.OrderItemDto
import kotlinx.coroutines.delay

class FakeOrderRemoteDataSource : OrderRemoteDataSource {
    override suspend fun getOrderById(orderId: Int): OrderDto {
        delay(500)
        return OrderDto(
            id = orderId,
            items = listOf(
                OrderItemDto("برجر كلاسيك", 1, 89.0),
                OrderItemDto("بطاطس مقلية", 2, 69.0),
                OrderItemDto("كولا دايت", 1, 120.0)
            ),
            deliveryFee = 15.0,
            status = "PENDING"
        )
    }

    override suspend fun placeOrder(items: List<OrderItemDto>, paymentMethod: String): OrderDto {
        delay(1500)
        return OrderDto(
            id = (1000..9999).random(),
            items = items,
            deliveryFee = 15.0,
            status = "CONFIRMED"
        )
    }
}