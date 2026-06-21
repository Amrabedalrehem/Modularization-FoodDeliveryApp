package com.example.payment.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.Dark600
import com.example.designsystem.theme.Dark800
import com.example.designsystem.theme.Orange500
import com.example.designsystem.theme.TextPrimary
import com.example.designsystem.theme.TextSecondary

@Composable
fun PriceSummaryCard(subtotal: Double, deliveryFee: Double, total: Double) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Dark800)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            PriceLine(label = "المجموع الجزئي", value = "${subtotal.toInt()} ج.م", valueColor = TextPrimary)
            PriceLine(label = "رسوم التوصيل",   value = "${deliveryFee.toInt()} ج.م", valueColor = TextSecondary)
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Dark600)
            PriceLine(label = "الإجمالي", value = "${total.toInt()} ج.م", valueColor = Orange500, isBold = true)
        }
    }
}

@Composable
private fun PriceLine(label: String, value: String, valueColor: Color, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label, color = TextSecondary,
            fontSize = if (isBold) 16.sp else 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = value, color = valueColor,
            fontSize = if (isBold) 18.sp else 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}