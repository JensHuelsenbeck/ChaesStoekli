package com.example.cheas_stoeckli.ui.components.More

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.cheas_stoeckli.ui.theme.buttonHighlight
import com.example.cheas_stoeckli.ui.theme.buttonPrimary
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@Composable
fun ContactCard(
) {

    val context = LocalContext.current
    val phoneNumber = "+41447616194"
    val email = "mail@chaesstoeckli.ch"
    val website = "https://chaesstoeckli.ch/Grueezi-Wohl/"
    val orderSite = "https://www.chaesstoeckli.ch/Bstellige-Kontakt/"
    val adress = "Zürichstrasse 106 8910 Affoltern am Albis"


    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardText("Chäs Stöckli AG", Color.Black)
            CardText("Zürichstrasse 106", Color.Black)
            CardText("8910 Affoltern am Albis", Color.Black)
            CardText("+41 44 761 61 94", Color.Black)
            CardText("mail@chaesstoeckli.ch", Color.Black)
            Spacer(Modifier.height(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ContactCardButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = "geo:0,0?q=$adress".toUri()
                            }
                            context.startActivity(intent)
                        },
                        text = "Anfahrt",
                        color = buttonHighlight
                    )
                    ContactCardButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = "tel:$phoneNumber".toUri()
                            context.startActivity(intent)
                        },
                        text = "Anruf",
                        color = buttonHighlight
                    )
                }
                Spacer(Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    ContactCardButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_SENDTO).apply {
                                data = "mailto:$email".toUri()
                            }
                            context.startActivity(intent)
                        },
                        text = "Email",
                        color = buttonPrimary
                    )
                    ContactCardButton(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW).apply {
                                data = website.toUri()
                            }
                            context.startActivity(intent)
                        },
                        text = "Website",
                        color = buttonPrimary
                    )
                }
                Spacer(Modifier.height(4.dp))
                ContactCardButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            data = orderSite.toUri()
                        }
                        context.startActivity(intent)
                    },
                    text = "Bestellen",
                    color = buttonPrimary
                )
            }
        }
    }
}

@Composable
fun CardText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
    Spacer(Modifier.height(2.dp))
}