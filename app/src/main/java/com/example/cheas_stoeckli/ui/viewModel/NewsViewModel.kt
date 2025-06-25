package com.example.cheas_stoeckli.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.Fake.newsList
import com.example.cheas_stoeckli.data.repositories.NewsRepository
import com.example.cheas_stoeckli.domain.domain.usecases.SignOutUseCase
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class NewsViewModel(
    private val signOutUseCase: SignOutUseCase,
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val newsRepo: NewsRepository
) : ViewModel() {

    private val _news = MutableStateFlow(newsList)
    val news = _news.asStateFlow()


    val announcements: StateFlow<List<News>> =
        newsRepo.observeNews()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )


    fun deleteAnnouncement(news: News) {
        newsRepo.deleteAnnouncement(news)
    }

    fun onSignOutClick() {
        signOutUseCase()
    }

}

