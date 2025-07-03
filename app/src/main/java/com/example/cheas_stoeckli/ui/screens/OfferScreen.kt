package com.example.cheas_stoeckli.ui.screens

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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.components.Header
import com.example.cheas_stoeckli.ui.components.Offers.OfferAddDialog
import com.example.cheas_stoeckli.ui.components.Offers.OfferInformation
import com.example.cheas_stoeckli.ui.components.Offers.OfferList
import com.example.cheas_stoeckli.ui.enums.OfferKind
import com.example.cheas_stoeckli.ui.theme.loginButtonColor
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.OfferViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun OfferScreen(
    snackbarHostState: SnackbarHostState,
    snackbarScope: CoroutineScope,
    viewModel: OfferViewModel = koinViewModel(),
    onClickToDetailedOfferScreen: (OfferKind) -> Unit,
    modifier: Modifier = Modifier,
) {

    val offers = viewModel.offers.collectAsState()
    val appUser = viewModel.appUser.collectAsState()
    var showAddDialog = remember { mutableStateOf(false) }
    var showInfoDialog by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = screenBackgroundPrimary
    ) {
        Box {
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
                    Header(text = "s' Angebot")
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = { showInfoDialog = true }
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
                }

                OfferList(
                    offers = offers.value,
                    user = appUser.value,
                    viewModel = viewModel,
                    onClickToDetailedOfferScreen = onClickToDetailedOfferScreen
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
        if (showInfoDialog) {
            OfferInformation(
                user = appUser.value,
                onDismiss = { showInfoDialog = false }
            )
        }
        if (showAddDialog.value) {
            OfferAddDialog(
                isDialogOpen = showAddDialog,
                snackbarHostState = snackbarHostState,
                snackbarScope = snackbarScope
            )
        }
    }
}