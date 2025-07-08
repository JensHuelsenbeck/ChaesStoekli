package com.example.cheas_stoeckli.ui.components.Team

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.data.Fake.fakeMember
import com.example.cheas_stoeckli.domain.models.TeamMember
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.utils.RotatingPlaceholder


@Composable
fun TeamCard(
    user: User?,
    member: TeamMember,
    uri: Uri?,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier
) {

    var isDialogshown by rememberSaveable { mutableStateOf(false) }
    var showDetailDialog by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = modifier
            .fillMaxWidth(0.9f)

            .combinedClickable(
                onClick = { showDetailDialog = true },
                onLongClick = {
                    if (user?.permissonLevel == "1") isDialogshown = true else {
                    }
                }
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
                SubcomposeAsyncImage(
                    model = uri ?: member.imgDownloadPath,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(150.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    when (painter.state) {
                        is AsyncImagePainter.State.Loading -> {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)
                            }
                        }

                        is AsyncImagePainter.State.Error -> {
                            RotatingPlaceholder(drawableRes = R.drawable.cheesewheel_placeholder)
                        }

                        else -> {
                            SubcomposeAsyncImageContent()
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxHeight()) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = member.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        lineHeight = 18.sp,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = member.description,
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        color = Color.Black,

                    )

                }
            }
        }
    }
    Spacer(Modifier.height(8.dp))

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
                    onClickDelete()
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
            text = { Text("Die Peronal wirklich löschen?") },
            containerColor = cardBackgroundPrimary,
            textContentColor = Color.Black,
            titleContentColor = Color.Black,
            modifier = modifier
        )
    }
}

@Preview
@Composable
private fun TeamCardPreviews() {
TeamCard(
    user = null,
    member = fakeMember,
    uri = null,
    onClickDelete = {},

)
}