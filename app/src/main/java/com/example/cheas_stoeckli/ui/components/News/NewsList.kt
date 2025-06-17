package com.example.cheas_stoeckli.ui.components.News

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.cheas_stoeckli.data.model.News

@Composable
fun NewsList(
    news: List<News>
) {

    LazyColumn {

        items(news) { news ->
            NewsCard(news = news)
        }

    }

}