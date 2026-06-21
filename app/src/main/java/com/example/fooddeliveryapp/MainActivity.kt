package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.designsystem.theme.FoodDeliveryTheme
import com.example.home.view.HomeScreen
import com.example.home.viewmodel.HomeViewModel
import com.example.home.usecase.GetFeaturedItemsUseCase
import com.example.home.usecase.GetRestaurantsUseCase
import com.example.payment.view.PaymentScreen
import com.example.payment.viewmodel.PaymentViewModel
import com.example.payment.usecase.GetOrderDetailsUseCase
import com.example.payment.usecase.PlaceOrderUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val appContainer = (application as FoodDeliveryApplication).container
        val getRestaurantsUseCase = GetRestaurantsUseCase(appContainer.restaurantRepository)
        val getFeaturedItemsUseCase = GetFeaturedItemsUseCase(appContainer.restaurantRepository)
        
        val getOrderDetailsUseCase = GetOrderDetailsUseCase(appContainer.orderRepository)
        val placeOrderUseCase = PlaceOrderUseCase(appContainer.orderRepository)

        setContent {
            FoodDeliveryTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    composable("home") {
                        val homeViewModel: HomeViewModel = viewModel(
                            factory = HomeViewModel.provideFactory(
                                getRestaurantsUseCase,
                                getFeaturedItemsUseCase
                            )
                        )
                        
                        HomeScreen(
                            viewModel = homeViewModel,
                            onCheckoutClick = { orderId ->
                                navController.navigate("payment/$orderId")
                            }
                        )
                    }
                    composable(
                        route = "payment/{orderId}",
                        arguments = listOf(
                            navArgument("orderId") { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        val orderId = backStackEntry.arguments?.getInt("orderId") ?: 0
                        val paymentViewModel: PaymentViewModel = viewModel(
                            factory = PaymentViewModel.provideFactory(
                                getOrderDetailsUseCase,
                                placeOrderUseCase
                            )
                        )
                        
                        PaymentScreen(
                            orderId = orderId,
                            viewModel = paymentViewModel,
                            onBackClick = { navController.navigateUp() },
                            onPaymentSuccess = {
                                navController.popBackStack("home", inclusive = false)
                            }
                        )
                    }
                }
            }
        }
    }
}