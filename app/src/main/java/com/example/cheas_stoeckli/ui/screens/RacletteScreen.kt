package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.components.BackButton
import com.example.cheas_stoeckli.ui.components.FavoFilterItem
import com.example.cheas_stoeckli.ui.components.FavoFilterItemInverted
import com.example.cheas_stoeckli.ui.components.Header
import com.example.cheas_stoeckli.ui.components.Raclette.RacletteAddDialog
import com.example.cheas_stoeckli.ui.components.Raclette.RacletteHeader
import com.example.cheas_stoeckli.ui.components.Raclette.RacletteList
import com.example.cheas_stoeckli.ui.theme.loginButtonColor
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.Raclette.RacletteViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun RacletteScreen(
    snackbarHostState: SnackbarHostState,
    snackbarScope: CoroutineScope,
    viewModel: RacletteViewModel = koinViewModel(),
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
) {


    val raclette = viewModel.filteredRaclette.collectAsState()
    val appUser = viewModel.appUser.collectAsState()
    val showAddDialog = remember { mutableStateOf(false) }
    val isFavorite by viewModel.showFavored.collectAsState()

    val errorMessage = viewModel.uiMessage

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
                        .padding(vertical = 8.dp)
                ) {
                    BackButton(popBackStack = popBackStack)
                    Header(text = "Raclettesortiment")
                    Spacer(Modifier.weight(1f))
                }
                Spacer(Modifier.height(10.dp))
                RacletteHeader()
                Spacer(Modifier.height(16.dp))
                Row {
                    FavoFilterItemInverted(
                        isSelected = isFavorite,
                        text = "Alle anzeigen",
                        onClick = { viewModel.setShowFavoredToFalse()}
                    )
                    FavoFilterItem(
                        isSelected = isFavorite,
                        text = "Favoriten anzeigen",
                        onClick = { viewModel.setShowFavoredToTrue() },

                        )
                }
                Spacer(Modifier.height(8.dp))
                RacletteList(
                    raclette = raclette.value,
                    user = appUser.value,
                    viewModel = viewModel
                )
            }
            if (appUser.value?.permissionLevel == "1")
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
        if (showAddDialog.value) {
            RacletteAddDialog(
                isDialogOpen = showAddDialog,
                snackbarHostState = snackbarHostState,
                snackbarScope = snackbarScope
            )
        }
    }
    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(errorMessage)
            viewModel.uiMessage = ""
        }
    }
}