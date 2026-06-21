package com.example.fooddeliveryapp

import android.app.Application
import com.example.fooddeliveryapp.di.AppContainer
import com.example.fooddeliveryapp.di.DefaultAppContainer


class FoodDeliveryApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
