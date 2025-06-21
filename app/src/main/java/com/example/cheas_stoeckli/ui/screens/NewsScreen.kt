package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cheas_stoeckli.ui.components.Dialog.NewsAddDialog
import com.example.cheas_stoeckli.ui.components.Header
import com.example.cheas_stoeckli.ui.components.News.NewsList
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NewsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun NewsScreen(
    viewModel: NewsViewModel = koinViewModel()
) {
    val news = viewModel.news.collectAsState()
    var showDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(screenBackgroundPrimary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Header(text = "Grüezi Wohl")
                Spacer(Modifier.weight(1f))
                Button(
                    onClick = viewModel::onSignOutClick
                ) {
                    Text(text = "Ausloggen")
                }
                Button(
                    onClick = { showDialog.value = true }
                ) {
                    Text(text = "Add News")
                }
            }
            NewsList(news = news.value)
        }
    }
    if (showDialog.value) {
        NewsAddDialog(isDialogOpen = showDialog)
    }
}
