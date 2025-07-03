package com.example.cheas_stoeckli.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BackButton(
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier) {
    IconButton(
        onClick = { popBackStack() },
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Zurück",
            tint = Color.Black
        )
    }
}