package com.example.cheas_stoeckli.ui.components.More

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@Composable
fun ContactCard(
) {

    val context = LocalContext.current
    val phoneNumber = "+41447616194"
    val email = "mail@chaesstoeckli.ch"
    val website = "https://chaesstoeckli.ch/Grueezi-Wohl/"

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
            CardText("+41 44 761 61 94", Color.Blue, modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = "tel:$phoneNumber".toUri()
                context.startActivity(intent)
            })
            CardText("mail@chaesstoeckli.ch", Color.Blue, modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = "mailto:$email".toUri()
                }
                context.startActivity(intent) })
            CardText("Website", Color.Blue, modifier = Modifier.clickable {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = website.toUri()
                }
                context.startActivity(intent)
            })
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
        modifier = modifier
    )
    Spacer(Modifier.height(2.dp))
}