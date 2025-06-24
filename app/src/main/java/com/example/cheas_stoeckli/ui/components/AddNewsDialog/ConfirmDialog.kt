package com.example.cheas_stoeckli.ui.components.AddNewsDialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NewsAddViewModel

@Composable
fun ConfirmDialog(
    showConfirmDialog: MutableState<Boolean>,
    showAddDialog: MutableState<Boolean>,
    viewModel: NewsAddViewModel,
    confirmText: String,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = { },
        dismissButton = { TextButton(onClick = { showConfirmDialog.value = false }) { Text(
            text = "Abbrechen",
            color = Color.Blue,
            fontSize = 17.sp) } },
        confirmButton = { TextButton(onClick = {
            showAddDialog.value = false
            viewModel.setValuablesToEmpty()
        }) { Text(
            text = "Bestätigen",
            color = Color.Blue,
            fontSize = 17.sp) } },
        icon = { /* Icon */ },
        title = { Text("Achtung!") },
        text = { Text(
            text = confirmText,
            fontSize = 16.sp) },
        containerColor = cardBackgroundPrimary,
        textContentColor = Color.Black,
        titleContentColor = Color.Black,
        modifier = modifier

    )
}
