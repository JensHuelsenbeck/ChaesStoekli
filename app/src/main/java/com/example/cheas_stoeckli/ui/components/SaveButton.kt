package com.example.cheas_stoeckli.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.cheas_stoeckli.ui.theme.loginButtonColor

@Composable
fun SaveButton(
    text: String,
    onClickSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClickSave,
        colors = ButtonDefaults.buttonColors(containerColor = loginButtonColor ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth(0.6f)
            .height(55.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(6.dp)
        )
    }
}