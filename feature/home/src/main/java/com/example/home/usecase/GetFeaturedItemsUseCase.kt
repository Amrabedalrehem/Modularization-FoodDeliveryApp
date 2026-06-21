package com.example.home.usecase

import com.example.restaurants.model.FoodItem
import com.example.restaurants.repository.RestaurantRepository


class GetFeaturedItemsUseCase(
    private val repository: RestaurantRepository
) {
    suspend operator fun invoke(): List<FoodItem> {
        return repository.getFeaturedItems()
    }
}
