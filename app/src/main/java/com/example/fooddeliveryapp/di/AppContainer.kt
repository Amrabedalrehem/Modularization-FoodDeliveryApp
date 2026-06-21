package com.example.fooddeliveryapp.di

import com.example.order.datasource.FakeOrderRemoteDataSource
import com.example.order.repository.OrderRepository
import com.example.order.repository.OrderRepositoryImpl
import com.example.restaurants.datasource.FakeRestaurantRemoteDataSource
import com.example.restaurants.repository.RestaurantRepository
import com.example.restaurants.repository.RestaurantRepositoryImpl

interface AppContainer {
    val restaurantRepository: RestaurantRepository
    val orderRepository: OrderRepository
}

class DefaultAppContainer : AppContainer {
    private val fakeRestaurantRemoteDataSource by lazy {
        FakeRestaurantRemoteDataSource()
    }

    private val fakeOrderRemoteDataSource by lazy {
        FakeOrderRemoteDataSource()
    }

    override val restaurantRepository: RestaurantRepository by lazy {
        RestaurantRepositoryImpl(fakeRestaurantRemoteDataSource)
    }

    override val orderRepository: OrderRepository by lazy {
        OrderRepositoryImpl(fakeOrderRemoteDataSource)
    }
}
