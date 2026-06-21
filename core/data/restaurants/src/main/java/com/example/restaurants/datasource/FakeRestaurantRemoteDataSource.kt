package com.example.restaurants.datasource

import com.example.restaurants.model.remote.FoodItemDto
import com.example.restaurants.model.remote.RestaurantDto
import kotlinx.coroutines.delay

class FakeRestaurantRemoteDataSource : RestaurantRemoteDataSource {

    override suspend fun getRestaurants(): List<RestaurantDto> {
        delay(800)
        return fakeRestaurants
    }

    override suspend fun getFeaturedItems(): List<FoodItemDto> {
        delay(500)
        return fakeRestaurants.flatMap { it.menuItems }.filter { it.isFeatured }
    }

    private val fakeRestaurants = listOf(
        RestaurantDto(
            id = 1,
            name = "برجر بلس",
            imageUrl = "burger",
            rating = 4.8,
            deliveryTime = "20-30 دقيقة",
            category = "برجر",
            deliveryFee = 15.0,
            isOpen = true,
            menuItems = listOf(
                FoodItemDto(
                    1,
                    "دبل تشيز برجر",
                    "برجر لحم مع جبن مزدوج وخضار طازجة",
                    89.0,
                    "burger1",
                    "برجر",
                    true
                ),
                FoodItemDto(
                    2,
                    "برجر مشروم",
                    "برجر مع مشروم مشوي وصوص بني",
                    79.0,
                    "burger2",
                    "برجر",
                    false
                ),
                FoodItemDto(
                    3,
                    "كريسبي تشيكن",
                    "فراخ مقرمشة مع تتبيلة خاصة",
                    69.0,
                    "chicken1",
                    "فراخ",
                    true
                )
            )
        ),
        RestaurantDto(
            id = 2,
            name = "بيتزا ماما",
            imageUrl = "pizza",
            rating = 4.6,
            deliveryTime = "25-40 دقيقة",
            category = "بيتزا",
            deliveryFee = 20.0,
            isOpen = true,
            menuItems = listOf(
                FoodItemDto(
                    4,
                    "بيتزا مارجريتا",
                    "بيتزا كلاسيك مع صوص طماطم وجبن موتزاريلا",
                    95.0,
                    "pizza1",
                    "بيتزا",
                    true
                ),
                FoodItemDto(
                    5,
                    "بيتزا بيبروني",
                    "بيتزا مع بيبروني وجبن مزدوج",
                    110.0,
                    "pizza2",
                    "بيتزا",
                    false
                )
            )
        ),
        RestaurantDto(
            id = 3,
            name = "سوشي تايم",
            imageUrl = "sushi",
            rating = 4.9,
            deliveryTime = "30-45 دقيقة",
            category = "سوشي",
            deliveryFee = 25.0,
            isOpen = true,
            menuItems = listOf(
                FoodItemDto(
                    6,
                    "كاليفورنيا رول",
                    "روبيان وأفوكادو مع صوص تيرياكي",
                    120.0,
                    "sushi1",
                    "سوشي",
                    true
                ),
                FoodItemDto(
                    7,
                    "سالمون ساشيمي",
                    "شرائح سالمون طازجة مع صوص الصويا",
                    140.0,
                    "sushi2",
                    "سوشي",
                    true
                )
            )
        ),
        RestaurantDto(
            id = 4,
            name = "شاورما الأصل",
            imageUrl = "shawarma",
            rating = 4.7,
            deliveryTime = "15-25 دقيقة",
            category = "شاورما",
            deliveryFee = 10.0,
            isOpen = true,
            menuItems = listOf(
                FoodItemDto(
                    8,
                    "شاورما دجاج",
                    "شاورما دجاج مشوية مع توم وخضار",
                    55.0,
                    "shawarma1",
                    "شاورما",
                    true
                ),
                FoodItemDto(
                    9,
                    "شاورما لحم",
                    "لحم مشوي مع صوص الثوم والحار",
                    70.0,
                    "shawarma2",
                    "شاورما",
                    false
                )
            )
        ),
        RestaurantDto(
            id = 5,
            name = "باستا إيطاليانو",
            imageUrl = "pasta",
            rating = 4.5,
            deliveryTime = "35-50 دقيقة",
            category = "باستا",
            deliveryFee = 18.0,
            isOpen = false,
            menuItems = listOf(
                FoodItemDto(
                    10,
                    "كاربونارا",
                    "باستا بصوص كريمي مع بيكون وجبن بارميزان",
                    105.0,
                    "pasta1",
                    "باستا",
                    false
                )
            )
        )
    )
}