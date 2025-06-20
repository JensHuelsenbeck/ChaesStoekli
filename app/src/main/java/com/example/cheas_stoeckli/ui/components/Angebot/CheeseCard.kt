package com.example.cheas_stoeckli.ui.components.Angebot

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@Composable
fun CheeseCard(
    navigateToOffer: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(125.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = { }
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFFD9D9D9).copy(alpha = 0.6f))
                .clip(RoundedCornerShape(12.dp))
        ) {
Text(
    text = text,
    fontSize = 16.sp,
    color = Color.Black
)
        }
    }
}



