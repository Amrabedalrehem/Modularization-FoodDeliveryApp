package com.example.restaurants.repository

import com.example.restaurants.model.FoodItem
import com.example.restaurants.model.Restaurant


interface RestaurantRepository {
    suspend fun getRestaurants(): List<Restaurant>
    suspend fun getFeaturedItems(): List<FoodItem>
}
