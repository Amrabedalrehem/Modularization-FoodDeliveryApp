package com.example.restaurants.model.remote

data class RestaurantDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val deliveryTime: String,
    val category: String,
    val deliveryFee: Double,
    val isOpen: Boolean,
    val menuItems: List<FoodItemDto>
)

data class FoodItemDto(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isFeatured: Boolean
)
