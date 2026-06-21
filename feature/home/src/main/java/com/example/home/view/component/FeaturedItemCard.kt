package com.example.home.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.Dark700
import com.example.designsystem.theme.Dark800
import com.example.designsystem.theme.Orange500
import com.example.designsystem.theme.TextPrimary
import com.example.restaurants.model.FoodItem

@Composable
fun FeaturedItemCard(item: FoodItem) {
    Card(
        modifier = Modifier.width(160.dp).clickable { },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Dark700)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth().height(110.dp)
                    .background(Brush.linearGradient(listOf(Orange500.copy(alpha = 0.3f), Dark800))),
                contentAlignment = Alignment.Center
            ) {
                Text(text = getFoodEmoji(item.category), fontSize = 48.sp)
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = item.name, color = TextPrimary, fontSize = 13.sp,
                    fontWeight = FontWeight.Bold, maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${item.price.toInt()} ج.م", color = Orange500, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}