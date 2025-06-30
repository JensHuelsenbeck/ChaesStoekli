package com.example.cheas_stoeckli.ui.components.News

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cheas_stoeckli.data.Fake.fakeNews
import com.example.cheas_stoeckli.domain.models.News

class NewsDetailDialog(
    news: News
) {
    
}

@Preview
@Composable
private fun newspreview() {
    NewsDetailDialog(fakeNews)
}