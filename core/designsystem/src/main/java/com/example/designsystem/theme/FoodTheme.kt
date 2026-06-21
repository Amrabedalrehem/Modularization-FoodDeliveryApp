package com.example.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val FoodDarkColorScheme = darkColorScheme(
    primary = Orange500,
    onPrimary = TextPrimary,
    primaryContainer = Orange100,
    secondary = YellowStar,
    background = Dark900,
    surface = Dark800,
    surfaceVariant = Dark700,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    error = RedError
)


@Composable
fun FoodDeliveryTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FoodDarkColorScheme,
        content = content
    )
}
