package com.example.cheas_stoeckli.ui.components.More

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactCardButton(
    color: Color,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(containerColor = color ),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier
                .height(45.dp)
                .width(140.dp)
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(4.dp)
            )
        }

}