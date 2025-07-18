package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import com.example.cheas_stoeckli.ui.components.BackButton
import com.example.cheas_stoeckli.ui.components.Cheese.CheeseAddDialog
import com.example.cheas_stoeckli.ui.components.Cheese.CheeseEnumItem
import com.example.cheas_stoeckli.ui.components.Cheese.CheeseList
import com.example.cheas_stoeckli.ui.components.CustomFloatingActionButton
import com.example.cheas_stoeckli.ui.components.FavoFilterItem
import com.example.cheas_stoeckli.ui.components.FavoFilterItemInverted
import com.example.cheas_stoeckli.ui.components.Header
import com.example.cheas_stoeckli.ui.enums.MilkType
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.Cheese.CheeseViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheeseScreen(
    snackbarHostState: SnackbarHostState,
    snackbarScope: CoroutineScope,
    viewModel: CheeseViewModel = koinViewModel(),
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val milkType = viewModel.milkType.collectAsState()
    val cheese = viewModel.filteredCheese.collectAsState()
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
                    Header(text = "Käsesortiment")
                    Spacer(Modifier.weight(1f))

                        }
                Spacer(Modifier.height(10.dp))
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
                LazyRow(
                    Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    items(MilkType.entries) { entry ->
                        CheeseEnumItem(
                            enum = entry,
                            onClick = { viewModel.setMilkType(entry) },
                            isSelected = milkType.value == entry
                        )
                    }
                }
                Spacer(Modifier.height(8.dp))
                CheeseList(
                    cheese = cheese.value,
                    user = appUser.value,
                    viewModel = viewModel
                )
            }
            if (appUser.value?.permissionLevel == "1") {
                CustomFloatingActionButton(
                    onClick = { showAddDialog.value = true },
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }
        if (showAddDialog.value) {
            CheeseAddDialog(
                isDialogOpen = showAddDialog,
                snackbarHostState = snackbarHostState,
                snackbarScope = snackbarScope
            )
        }
        LaunchedEffect(errorMessage) {
            if (errorMessage.isNotEmpty()) {
                snackbarHostState.showSnackbar(errorMessage)
                viewModel.uiMessage = ""
            }
        }
    }
}
