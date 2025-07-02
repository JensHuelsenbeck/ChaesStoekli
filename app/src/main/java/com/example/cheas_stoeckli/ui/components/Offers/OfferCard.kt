package com.example.cheas_stoeckli.ui.components.Offers

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.utils.RotatingPlaceholder

@Composable
fun OfferCard(
    navigateToDetailedOffer: () -> Unit, offer: Offer, modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .heightIn(min = 100.dp)
            .combinedClickable(onClick = { navigateToDetailedOffer() }, onLongClick = { }),
        shape = RoundedCornerShape(12.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD9D9D9).copy(alpha = 0.6f))
                .clip(RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = offer.title,
                fontSize = 26.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            if (offer.text != "") {
                Text(
                    text = offer.text,
                    fontSize = 20.sp,
                    color = Color.Black,
                    )
                Spacer(Modifier.height(6.dp))
            }
            if (offer.bottomText != "") {
                Text(
                    text = offer.bottomText,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(6.dp))
            }
            if (offer.imgDownloadPath != "") {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 220.dp)
                        .padding(4.dp),
                    model = offer.imgDownloadPath,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    loading = {
                        RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)
                    })
            }
        }
    }
}

