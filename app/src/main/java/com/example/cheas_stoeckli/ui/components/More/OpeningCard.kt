package com.example.cheas_stoeckli.ui.components.More

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.More.MoreViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OpeningCard(
    modifier: Modifier = Modifier,
    viewModel: MoreViewModel = koinViewModel()
) {



    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDivider(thickness = 1.dp, color = Color.Black.copy(0.4f))
                viewModel.daysWithHours.forEach { (day, hours) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp, horizontal = 12.dp)
                    ) {
                        Text(
                            text = day,
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Text(
                            text = hours,
                            modifier = Modifier.weight(2f),
                            fontSize = 18.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                    HorizontalDivider(thickness = 1.dp, color = Color.Black.copy(0.4f))
                }
            }
        }
    }
