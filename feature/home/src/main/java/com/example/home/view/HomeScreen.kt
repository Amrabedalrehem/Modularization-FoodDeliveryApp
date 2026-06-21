package com.example.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.designsystem.theme.*
import com.example.home.viewmodel.HomeUiState
import com.example.home.viewmodel.HomeViewModel
import com.example.home.viewmodel.foodCategories
import com.example.home.view.component.CategoriesRow
import com.example.home.view.component.ErrorScreen
import com.example.home.view.component.FeaturedItemsRow
import com.example.home.view.component.HomeHeader
import com.example.home.view.component.HomeSearchBar
import com.example.home.view.component.LoadingScreen
import com.example.home.view.component.RestaurantCard
import com.example.restaurants.model.Restaurant


@Composable
fun HomeScreen(
    onCheckoutClick: (orderId: Int) -> Unit,
    viewModel: HomeViewModel
) {
    val uiState     by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Dark900)
    ) {
        when (val state = uiState) {
            is HomeUiState.Loading -> LoadingScreen()
            is HomeUiState.Error   -> ErrorScreen(
                message = state.message,
                onRetry  = viewModel::retry
            )
            is HomeUiState.Success -> HomeContent(
                state             = state,
                searchQuery       = searchQuery,
                onSearchChanged   = viewModel::onSearchQueryChanged,
                onCategorySelected= viewModel::onCategorySelected,
                onRestaurantClick = { restaurant -> onCheckoutClick(restaurant.id) }
            )
        }
    }
}

@Composable
private fun HomeContent(
    state: HomeUiState.Success,
    searchQuery: String,
    onSearchChanged: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onRestaurantClick: (Restaurant) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item { HomeHeader() }

        item {
            HomeSearchBar(
                query          = searchQuery,
                onQueryChanged = onSearchChanged,
                modifier       = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )
        }

        item {
            SectionTitle(title = "الأصناف", topPadding = 20.dp)
            CategoriesRow(
                categories         = foodCategories,
                selectedCategory   = state.selectedCategory,
                onCategorySelected = onCategorySelected
            )
        }

        if (state.featuredItems.isNotEmpty()) {
            item {
                SectionTitle(title = "🔥 الأكثر طلباً", topPadding = 24.dp)
                FeaturedItemsRow(items = state.featuredItems)
            }
        }

        item { SectionTitle(title = "المطاعم القريبة منك", topPadding = 24.dp) }

        if (state.restaurants.isEmpty()) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "لا توجد مطاعم في هذا الصنف", color = TextSecondary, fontSize = 16.sp)
                }
            }
        } else {
            items(state.restaurants, key = { it.id }) { restaurant ->
                    RestaurantCard(
                        restaurant = restaurant,
                        onClick    = { onRestaurantClick(restaurant) },
                        modifier   = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                    )
            }
        }
    }
}


@Composable
private fun SectionTitle(title: String, topPadding: Dp = 0.dp) {
    Text(
        text     = title,
        color    = TextPrimary,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 20.dp, top = topPadding, bottom = 12.dp)
    )
}