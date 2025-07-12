package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.components.Team.TeamAddDialog
import com.example.cheas_stoeckli.ui.components.Team.TeamHeader
import com.example.cheas_stoeckli.ui.components.Team.TeamList
import com.example.cheas_stoeckli.ui.theme.loginButtonColor
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.Team.TeamViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeamScreen(
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    snackbarScope: CoroutineScope,
    viewModel: TeamViewModel = koinViewModel(),) {


    val appUser = viewModel.appUser.collectAsState()
    val team = viewModel.teamMembers.collectAsState()
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
                TeamHeader()
                Spacer(Modifier.height(8.dp))
                TeamList(
                    user = appUser.value,
                    team = team.value,
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

            if (showAddDialog.value) {
                TeamAddDialog(
                    isDialogOpen = showAddDialog,
                    snackbarHostState = snackbarHostState,
                    snackbarScope = snackbarScope
                )
            }
        }
    }
}
