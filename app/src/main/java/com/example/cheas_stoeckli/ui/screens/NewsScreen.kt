package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cheas_stoeckli.ui.components.Header
import com.example.cheas_stoeckli.ui.components.News.NewsList
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NewsViewModel


@Composable
fun NewsScreen(
    viewModel: NewsViewModel = viewModel()
) {
    val news = viewModel.news.collectAsState()

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
                    onClick = { /*TODO*/ }) {
                }
            }
            NewsList(news = news.value)
        }
    }
}
