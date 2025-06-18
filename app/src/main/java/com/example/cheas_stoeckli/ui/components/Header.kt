
package com.example.cheas_stoeckli.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun Header(text: String) {

    Text(
        text = text,
        fontSize = 30.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}

@Preview
@Composable
private fun HeaderPreview() {
    Header("Grüetzi Wohl")
}

