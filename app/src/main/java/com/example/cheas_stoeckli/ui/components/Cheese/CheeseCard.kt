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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.ui.components.LoginDialog
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.Cheese.CheeseViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CheeseCard(
    cheese: Cheese,
    user: User?,
    onClickDelete: () -> Unit,
    viewModel: CheeseViewModel?

) {

    val anonymousUser = FirebaseAuth.getInstance().currentUser

    var favoriteCheeseIds = viewModel?.favoriteCheeseIds?.collectAsState()
    var isDialogshown by remember { mutableStateOf(false) }

    val loginDialog = remember { mutableStateOf(false) }

    val onFavoriteToggle = {
        viewModel?.let {
            if (cheese.id in favoriteCheeseIds?.value.orEmpty()) {
                it.deleteCheeseFromFavorites(cheese.id)
            } else {
                it.addCheeseToFavorites(cheese.id)
            }
        }
    }

    Card(
        colors = CardDefaults.cardColors(cardBackgroundPrimary),
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    if (user?.permissionLevel == "1") isDialogshown = true else {
                    }
                }
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
            Row() {
                Text(
                    text = "• ",
                    fontSize = 25.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = cheese.name,
                    fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
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
            ) {
                IconButton(
                    onClick = {
                        if (anonymousUser?.isAnonymous == true) {
                            loginDialog.value = true
                        } else {
                            onFavoriteToggle()
                        }
                    }
                ) {
                    if (cheese.id in favoriteCheeseIds?.value.orEmpty()) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.favo_herz
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .height(30.dp)
                                .width(30.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.outline_favorite_24),
                            contentDescription = null,
                        )
                    }
                }
            }
            Spacer(Modifier.height(6.dp))
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
                    onClickDelete()
                    isDialogshown = false
                }) {
                    Text(
                        text = "Löschen",
                        color = Color.Blue
                    )
                }
            },
            icon = { },
            title = { Text("Achtung!") },
            text = { Text("Den Käse wirklich löschen?") },
            containerColor = cardBackgroundPrimary,
            textContentColor = Color.Black,
            titleContentColor = Color.Black
        )
    }
    if(loginDialog.value) LoginDialog(
        isDialogshown = loginDialog
    )

}
