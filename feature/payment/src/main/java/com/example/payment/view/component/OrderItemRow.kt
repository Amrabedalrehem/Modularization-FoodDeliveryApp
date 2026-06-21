package com.example.payment.view.component
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.Dark800
import com.example.designsystem.theme.Orange500
import com.example.designsystem.theme.TextPrimary
import com.example.designsystem.theme.TextSecondary
import com.example.payment.viewmodel.OrderItemUi

@Composable
fun OrderItemRow(item: OrderItemUi) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Dark800, RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, color = TextPrimary, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            Text(text = "× ${item.quantity}", color = TextSecondary, fontSize = 12.sp)
        }
        Text(
            text = "${(item.price * item.quantity).toInt()} ج.م",
            color = Orange500, fontSize = 14.sp, fontWeight = FontWeight.Bold
        )
    }
}