package com.example.cheas_stoeckli.ui.components.Dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.ui.enums.NewsEnum

@Composable
fun EnumItem(
    enum: NewsEnum,
    onClick: () -> Unit,
    modifier: Modifier = Modifier) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(40.dp)

            .padding(horizontal = 4.dp )
            .background(Color.Transparent, RoundedCornerShape(12.dp))
            .border(2.dp, Color.Black, shape = RoundedCornerShape(12.dp))
            .clickable(
                onClick = { onClick }
            )
    ) {
        Text(
            text = enum.rawValue,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(horizontal = 6.dp)
        )
    }

}