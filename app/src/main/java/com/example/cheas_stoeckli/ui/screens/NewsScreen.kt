package com.example.cheas_stoeckli.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.components.AddNewsDialog.NewsAddDialog
import com.example.cheas_stoeckli.ui.components.Header
import com.example.cheas_stoeckli.ui.components.News.NewsInformation
import com.example.cheas_stoeckli.ui.components.News.NewsList
import com.example.cheas_stoeckli.ui.theme.loginButtonColor
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NewsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel


@Composable
fun NewsScreen(
    snackbarHostState: SnackbarHostState,
    snackbarScope: CoroutineScope,
    viewModel: NewsViewModel = koinViewModel()
) {

    val appUser = viewModel.appUser.collectAsState()
    val infoDialog = viewModel.InfoDialog.collectAsState()
    val annoucements = viewModel.announcements.collectAsState()
    val showAddDialog = remember { mutableStateOf(false) }
    val showInfoDialog = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = screenBackgroundPrimary
    ) {
        Box() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Header(text = "Grüezi Wohl")
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = { showInfoDialog.value = true }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(loginButtonColor, shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "?",
                                fontSize = 24.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Button(
                        onClick =
                            {
                                viewModel.onSignOutClick()
                                //viewModel.hasSeenInfoDialog(false)
                            }
                    ) {
                        Text("+")
                    }
                }
                NewsList(
                    news = annoucements.value,
                    newsViewModel = viewModel,
                    user = appUser.value
                )
            }
            if (appUser.value?.permissonLevel == "1")
                FloatingActionButton(
                    onClick = { showAddDialog.value = true },
                    containerColor = loginButtonColor,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(horizontal = 16.dp, vertical = 28.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.write_square_24),
                        contentDescription = "Beitrag schreiben"
                    )
                }
        }
        if (showInfoDialog.value) {
            NewsInformation(
                user = appUser.value,
                onDismiss = {
                    showInfoDialog.value = false
                    viewModel.hasSeenInfoDialog(seen = true)
                }
            )
        }
        if (showAddDialog.value) {
            NewsAddDialog(
                isDialogOpen = showAddDialog,
                snackbarHostState = snackbarHostState,
                snackbarScope = snackbarScope
            )
        }
        LaunchedEffect(Unit) {
            delay(1000L)
            Log.d(
                "NewsScreen",
                "LaunchedEffect gestartet – InfoDialog in ViewModel: ${infoDialog.value}"
            )
            if (!infoDialog.value) {
                Log.d("NewsScreen", "InfoDialog wird angezeigt (showInfoDialog = true)")
                showInfoDialog.value = true

            }
        }
    }
}

