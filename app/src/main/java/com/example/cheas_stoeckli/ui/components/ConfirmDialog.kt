package com.example.cheas_stoeckli.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiermDialog(
    isMainDialogShown: MutableState<Boolean>,
    onDissmiss: () -> Unit,
    onAccept: () -> Unit,
    modifier: Modifier = Modifier
) {

    Dialog(
        onDismissRequest = TODO()
    ) {

    }

}