
package com.example.payment.view.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.Orange500
import com.example.designsystem.theme.TextSecondary

@Composable
fun OrderIdBadge(orderId: Int) {
    Surface(shape = RoundedCornerShape(12.dp), color = Orange500.copy(alpha = 0.1f)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "🛒", fontSize = 24.sp)
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "رقم الطلب", color = TextSecondary, fontSize = 12.sp)
                Text(
                    text = "#${orderId.toString().padStart(4, '0')}",
                    color = Orange500, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
