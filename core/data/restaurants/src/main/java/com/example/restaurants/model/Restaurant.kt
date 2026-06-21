package com.example.restaurants.model


data class Restaurant(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val deliveryTime: String,
    val category: String,
    val deliveryFee: Double,
    val isOpen: Boolean,
    val menuItems: List<FoodItem>
)

data class FoodItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val isFeatured: Boolean
)
