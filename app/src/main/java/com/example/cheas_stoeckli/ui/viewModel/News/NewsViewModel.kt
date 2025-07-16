package com.example.cheas_stoeckli.ui.viewModel.News

import android.util.Log
import androidx.datastore.core.IOException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.News.NewsRepository
import com.example.cheas_stoeckli.data.repositories.UserPrefRepository
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val newsRepo: NewsRepository,
    private val userPrefRepo: UserPrefRepository
) : ViewModel() {

    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
        )

    val InfoDialog: StateFlow<Boolean> = userPrefRepo.hasSeenLocationDialog.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val announcements: StateFlow<List<News>> = newsRepo.observeNews().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun deleteAnnouncement(news: News) {
        newsRepo.deleteAnnouncement(news)
    }

    fun hasSeenInfoDialog(seen: Boolean) {
        viewModelScope.launch {
            viewModelScope.launch {
                try {
                    userPrefRepo.setHasSeenInfoDialog(seen)
                } catch (e: IOException) {
                    Log.e("NewsViewModel", "Konnte Dialog-Flag nicht speichern", e)
                }
            }
        }
    }

}

