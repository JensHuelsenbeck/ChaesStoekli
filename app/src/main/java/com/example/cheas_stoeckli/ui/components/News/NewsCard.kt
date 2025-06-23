package com.example.cheas_stoeckli.ui.components.News

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.theme.eventTimeAndDate
import com.example.cheas_stoeckli.ui.theme.newsEnumColor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsCard(
    news: News,
) {
    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(200.dp)
            .combinedClickable(
                onClick = {  },
                onLongClick = {  }
            ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFFD9D9D9).copy(alpha = 0.6f))
                .clip(RoundedCornerShape(12.dp))

        ) {
            Box {
                AsyncImage(
                    model = news.imgDownloadPath,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.default_image),
                    error = painterResource(R.drawable.broken_image_ja),
                    onLoading = { Log.d("AsyncImage", "Image loading") },
                    onError = { state -> Log.e("AsyncImage", "Error loading image") },
                    onSuccess = { state -> Log.d("AsyncImage", "Image loaded successfully") },
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(136.dp)


                )
                Text(
                    text = news.type.rawValue,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .background(newsEnumColor)
                        .padding(vertical = 4.dp)
                        .width(136.dp)
                        .align(Alignment.BottomCenter)
                )
            }
            Box(modifier = Modifier.fillMaxHeight()) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(
                        text = news.title,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        lineHeight = 18.sp,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = news.text,
                        fontSize = 13.sp,
                        lineHeight = 18.sp,
                        color = Color.Black,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis

                    )

                }
                if (news.type.name == "EVENTS" || news.type.name == "REMINDER") {
                    Text(
                        text = news.date + " " + news.time,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 8.dp
                                )
                            )
                            .background(eventTimeAndDate)
                            .padding(4.dp)
                            .align(Alignment.BottomStart)
                    )
                }
                if(news.destination != "") {
                    Text(
                        text = "Anfahrt",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue,

                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 8.dp
                                )
                            )
                            .background(eventTimeAndDate)
                            .padding(4.dp)
                            .align(Alignment.BottomEnd)
                    )
                }
            }
        }
    }
    Spacer(Modifier.height(8.dp))
}

