package com.example.cheas_stoeckli.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@Composable
fun LoginDialog(
    authenticationService: AuthenticationService = AuthenticationService(),
    isDialogshown: MutableState<Boolean>,
) {

    AlertDialog(
        onDismissRequest = {},
        dismissButton = {
            TextButton(onClick = {
                isDialogshown.value = false
            }) {
                Text(
                    text = "Anonym bleiben",
                    color = Color.Blue
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
               authenticationService.signOut()
            }) {
                Text(
                    text = "Zum Login?",
                    color = Color.Blue
                )
            }
        },
        icon = { },
        title = { Text("Information zu den Favoriten!") },
        text = { Text("Um Favoriten setzen zu können, müssen Sie sich über Google anmelden.") },
        containerColor = cardBackgroundPrimary,
        textContentColor = Color.Black,
        titleContentColor = Color.Black
    )
}
