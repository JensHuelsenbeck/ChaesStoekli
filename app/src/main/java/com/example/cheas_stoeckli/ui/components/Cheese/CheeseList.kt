package com.example.cheas_stoeckli.ui.components.Cheese

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.domain.models.Cheese

@Composable
fun CheeseList(
cheese: List<Cheese>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (cheese.isEmpty()) {
            Text(
                text = "Noch keine Einträge",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(8.dp),
            ) {
                items(cheese) { item ->
                   CheeseCard(
                       cheese = item
                   )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }


}