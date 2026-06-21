package com.example.restaurants.repository

import com.example.restaurants.datasource.RestaurantRemoteDataSource
import com.example.restaurants.mapper.toDomain
import com.example.restaurants.model.FoodItem
import com.example.restaurants.model.Restaurant


class RestaurantRepositoryImpl(
    private val remoteDataSource: RestaurantRemoteDataSource
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> {
        return remoteDataSource.getRestaurants().map { it.toDomain() }
    }

    override suspend fun getFeaturedItems(): List<FoodItem> {
        return remoteDataSource.getFeaturedItems().map { it.toDomain() }
    }
}
