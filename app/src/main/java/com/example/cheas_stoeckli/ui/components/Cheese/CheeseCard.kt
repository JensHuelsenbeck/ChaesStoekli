package com.example.cheas_stoeckli.ui.components.Cheese

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.data.Fake.fakeCheese
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@Composable
fun CheeseCard(
    cheese: Cheese
) {

    var isFavorite by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .heightIn(min = 100.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = { }
            ),
        shape = RoundedCornerShape(12.dp)
    )
    {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9).copy(alpha = 0.6f))
                .clip(RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "• ${cheese.name}",
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = cheese.description,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 30.dp)

            )
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ){
             IconButton(
                 onClick = { isFavorite = !isFavorite }
             ) {
                 Image(
                     painter = painterResource(id = if(isFavorite) R.drawable.favorite_24 else R.drawable.favorite_border_24),
                     contentDescription = null,

                 )
             }
            }
            Spacer(Modifier.height(6.dp))
        }
    }
}

@Preview
@Composable
private fun CardPreview() {
    CheeseCard(fakeCheese)
}