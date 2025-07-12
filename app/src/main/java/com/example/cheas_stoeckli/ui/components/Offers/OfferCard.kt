package com.example.cheas_stoeckli.ui.components.Offers

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.ui.enums.OfferKind
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.utils.RotatingPlaceholder

@Composable
fun OfferCard(
    onClickToDetailedOfferScreen: (OfferKind) -> Unit,
    onclickDelete: () -> Unit,
    uri: Uri?,
    offer: Offer,
    user: User?,
    modifier: Modifier = Modifier
) {

    var isDialogshown by remember { mutableStateOf(false) }

    Log.d("OfferCardUri","URI im Dialog: $uri")

    if (offer.type == OfferKind.ALLGEMEIN) {
        Card(
            colors = CardDefaults.cardColors(cardBackgroundPrimary),
            modifier = modifier
                .fillMaxWidth(0.9f)
                .heightIn(min = 100.dp)
                .combinedClickable(
                    onClick = { },
                    onLongClick = {
                        if (user?.permissionLevel == "1") isDialogshown = true else {
                        }
                    }),
            shape = RoundedCornerShape(12.dp)
        ) {
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
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Spacer(Modifier.height(8.dp))
                if (offer.text != "") {
                    Text(
                        text = offer.text,
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                    Spacer(Modifier.height(6.dp))
                }
                if (offer.bottomText != "") {
                    Text(
                        text = offer.bottomText,
                        fontSize = 15.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                }
                if (uri != null || offer.imgDownloadPath.isNotBlank())
                    SubcomposeAsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = uri ?: offer.imgDownloadPath,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        loading = {
                            RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)
                        })
            }
        }
    } else {
        Card(
            colors = CardDefaults.cardColors(cardBackgroundPrimary),
            modifier = modifier
                .fillMaxWidth(0.9f)
                .height(125.dp)
                .combinedClickable(
                    onClick = {  onClickToDetailedOfferScreen(offer.type) },
                    onLongClick = { }
                ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier. fillMaxSize()
            ) {
                Text(
                    text = offer.title,
                    fontSize = 26.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
    if (isDialogshown) {
        AlertDialog(
            onDismissRequest = {},
            dismissButton = {
                TextButton(onClick = {
                    isDialogshown = false
                }) {
                    Text(
                        text = "Abbrechen",
                        color = Color.Blue
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    onclickDelete()
                    isDialogshown = false
                }) {
                    Text(
                        text = "Löschen",
                        color = Color.Blue
                    )
                }
            },
            icon = { /* Icon */ },
            title = { Text("Achtung!") },
            text = { Text("Die Ankündigung wirklich löschen?") },
            containerColor = cardBackgroundPrimary,
            textContentColor = Color.Black,
            titleContentColor = Color.Black,
            modifier = modifier
        )
    }
}

