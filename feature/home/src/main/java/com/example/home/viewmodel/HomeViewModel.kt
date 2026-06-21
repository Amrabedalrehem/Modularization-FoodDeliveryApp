package com.example.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.home.usecase.GetFeaturedItemsUseCase
import com.example.home.usecase.GetRestaurantsUseCase
import com.example.restaurants.model.Restaurant
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getRestaurantsUseCase: GetRestaurantsUseCase,
    private val getFeaturedItemsUseCase: GetFeaturedItemsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    private var allRestaurants: List<Restaurant> = emptyList()
    private var currentCategory: String = "الكل"

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                 val restaurantsDeferred = async { getRestaurantsUseCase() }
                val featuredDeferred = async { getFeaturedItemsUseCase() }

                val restaurants = restaurantsDeferred.await()
                val featured = featuredDeferred.await()

                allRestaurants = restaurants
                _uiState.value = HomeUiState.Success(
                    restaurants = restaurants,
                    featuredItems = featured
                )
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(
                    message = "حدث خطأ أثناء تحميل البيانات: ${e.message}"
                )
            }
        }
    }

    fun onCategorySelected(category: String) {
        currentCategory = category
        val currentState = _uiState.value
        if (currentState is HomeUiState.Success) {
            _uiState.update {
                currentState.copy(
                    restaurants = applyFilters(_searchQuery.value, category),
                    selectedCategory = category
                )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        val currentState = _uiState.value
        if (currentState is HomeUiState.Success) {
            _uiState.update {
                currentState.copy(restaurants = applyFilters(query, currentCategory))
            }
        }
    }

    private fun applyFilters(query: String, category: String): List<Restaurant> {
        return allRestaurants
            .filter { restaurant ->
                category == "الكل" || restaurant.category == category
            }
            .filter { restaurant ->
                query.isBlank() ||
                    restaurant.name.contains(query, ignoreCase = true) ||
                    restaurant.category.contains(query, ignoreCase = true)
            }
    }

    fun retry() {
        loadData()
    }

    companion object {
        fun provideFactory(
            getRestaurantsUseCase: GetRestaurantsUseCase,
            getFeaturedItemsUseCase: GetFeaturedItemsUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                HomeViewModel(
                    getRestaurantsUseCase = getRestaurantsUseCase,
                    getFeaturedItemsUseCase = getFeaturedItemsUseCase
                )
            }
        }
    }
}

val foodCategories = listOf("الكل", "برجر", "بيتزا", "سوشي", "شاورما", "باستا")