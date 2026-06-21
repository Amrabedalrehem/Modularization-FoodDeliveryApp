package com.example.restaurants.datasource

import com.example.restaurants.model.remote.FoodItemDto
import com.example.restaurants.model.remote.RestaurantDto

interface RestaurantRemoteDataSource {
    suspend fun getRestaurants(): List<RestaurantDto>
    suspend fun getFeaturedItems(): List<FoodItemDto>
}
