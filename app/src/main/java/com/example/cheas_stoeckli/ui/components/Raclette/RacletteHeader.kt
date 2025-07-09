package com.example.cheas_stoeckli.ui.components.Raclette

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@Composable
fun RacletteHeader(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.86f)
            .background(cardBackgroundPrimary, shape = RoundedCornerShape(12.dp))
            .padding(8.dp)

    ) {
        Image(
            painter = painterResource(id = R.drawable.raclette_bild),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Text(
            text = "Ob als Käsemischung im Beutel oder " +
                    "Fix Fertig im Chesseli, unser Fondue " +
                    "gibt es das ganze Jahr frisch auf " +
                    "Wunschgrösse zubereitet im Geschäft," +
                    "oder via Homepage gerne auch zum " +
                    "vorbestellen." +
                    "\n",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Die Hausmischung und das Träumli gibts Fix-fertig auch ausserhalb der " +
                    "Öffnungszeiten in verschiedenen Grössen im Fondue-Automat vor dem Geschäft.",
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
