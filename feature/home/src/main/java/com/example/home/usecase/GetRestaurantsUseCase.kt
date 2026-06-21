package com.example.home.usecase

import com.example.restaurants.model.Restaurant
import com.example.restaurants.repository.RestaurantRepository


class GetRestaurantsUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(): List<Restaurant> {
        return repository.getRestaurants()
    }
}
