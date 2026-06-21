package com.example.payment.view.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.theme.Dark600
import com.example.designsystem.theme.Orange500

@Composable
fun ConfirmPaymentButton(total: Double, isProcessing: Boolean, onConfirm: () -> Unit) {
    Button(
        onClick = onConfirm,
        modifier = Modifier.fillMaxWidth().height(56.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange500,
            disabledContainerColor = Dark600
        ),
        enabled = !isProcessing
    ) {
        if (isProcessing) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = "جاري المعالجة...", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        } else {
            Text(text = "ادفع ${total.toInt()} ج.م 🎉", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}