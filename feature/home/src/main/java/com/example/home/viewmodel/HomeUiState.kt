package com.example.home.viewmodel

import com.example.restaurants.model.FoodItem
import com.example.restaurants.model.Restaurant

sealed interface HomeUiState {
    data object Loading : HomeUiState

    data class Success(
        val restaurants: List<Restaurant> = emptyList(),
        val featuredItems: List<FoodItem> = emptyList(),
        val selectedCategory: String = "الكل"
    ) : HomeUiState

    data class Error(val message: String) : HomeUiState
}
