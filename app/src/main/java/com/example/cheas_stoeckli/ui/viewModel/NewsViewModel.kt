package com.example.cheas_stoeckli.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.data.Fake.newsList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel: ViewModel() {

    private val _news = MutableStateFlow(newsList)
    val news = _news.asStateFlow()

}