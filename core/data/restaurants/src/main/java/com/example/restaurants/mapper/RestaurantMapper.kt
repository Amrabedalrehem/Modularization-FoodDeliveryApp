package com.example.restaurants.mapper

import com.example.restaurants.model.remote.FoodItemDto
import com.example.restaurants.model.remote.RestaurantDto
import com.example.restaurants.model.FoodItem
import com.example.restaurants.model.Restaurant


fun RestaurantDto.toDomain(): Restaurant = Restaurant(
    id = id,
    name = name,
    imageUrl = imageUrl,
    rating = rating,
    deliveryTime = deliveryTime,
    category = category,
    deliveryFee = deliveryFee,
    isOpen = isOpen,
    menuItems = menuItems.map { it.toDomain() }
)

fun FoodItemDto.toDomain(): FoodItem = FoodItem(
    id = id,
    name = name,
    description = description,
    price = price,
    imageUrl = imageUrl,
    category = category,
    isFeatured = isFeatured
)
