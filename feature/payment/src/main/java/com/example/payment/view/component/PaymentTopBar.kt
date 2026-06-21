package com.example.payment.view.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.designsystem.theme.Dark800
import com.example.designsystem.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "OOU.O U. O U,OU,O\"", color = TextPrimary, fontWeight = FontWeight.Bold)
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "OOU^O1",
                    tint = TextPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Dark800)
    )
}
