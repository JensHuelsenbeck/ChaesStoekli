package com.example.cheas_stoeckli.ui.components.Angebot

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@Composable
fun CheeseCard(modifier: Modifier = Modifier) {

    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(200.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = { }
            ),
        shape = RoundedCornerShape(12.dp)
    ) {

    }

}

