package com.example.cheas_stoeckli.ui.components.News

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.ui.viewModel.NewsViewModel

@Composable
fun NewsList(
    user: User?,
    news: List<News>,
    newsViewModel: NewsViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (news.isEmpty()) {
            Text(
                text = "Noch keine Einträge",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(8.dp),
            ) {
                items(news) { item ->
                    NewsCard(
                        news = item,
                        uri = null,
                        onClickDelete = { newsViewModel.deleteAnnouncement(item) },
                        user = user
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

}