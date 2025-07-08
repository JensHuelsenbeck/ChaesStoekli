package com.example.cheas_stoeckli.ui.components.Team

import androidx.compose.foundation.Image
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

@Composable
fun TeamHeader(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth(0.9f)

    ) {
        Image(
            painter = painterResource(id = R.drawable.stoeckli_team_2019_04),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Text(
            text = "Seit über 60 Jahren ist der Chäs Stöckli fester Bestandteil von Affoltern am Albis." +
                    "\n",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "Gegründet von Johann und Margrit Stöckli, " +
                    "wird das traditionsreiche Käsefachgeschäft heute in zweiter und dritter " +
                    "Generation von Stefan und Michael Stöckli weitergeführt .",
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
