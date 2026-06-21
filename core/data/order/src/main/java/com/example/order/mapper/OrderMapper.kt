package com.example.order.mapper

import com.example.order.model.remote.OrderDto
import com.example.order.model.remote.OrderItemDto
import com.example.order.model.Order
import com.example.order.model.OrderItem

fun OrderDto.toDomain(): Order = Order(
    id = id,
    items = items.map { it.toDomain() },
    deliveryFee = deliveryFee,
    status = status
)

fun OrderItemDto.toDomain(): OrderItem = OrderItem(
    name = name,
    quantity = quantity,
    price = price
)

fun OrderItem.toDto(): OrderItemDto = OrderItemDto(
    name = name,
    quantity = quantity,
    price = price
)
