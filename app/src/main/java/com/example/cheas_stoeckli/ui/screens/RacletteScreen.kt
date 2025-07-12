package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.cheas_stoeckli.ui.components.BackButton
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


    val raclette = viewModel.raclette.collectAsState()
    val appUser = viewModel.appUser.collectAsState()
    val showAddDialog = remember { mutableStateOf(false) }

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
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .padding(end = 16.dp)
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
                Spacer(Modifier.height(20.dp))
                RacletteHeader()
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
}