package com.example.cheas_stoeckli.ui.viewModel.News

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.IOException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.News.NewsRepository
import com.example.cheas_stoeckli.data.repositories.UserPrefRepository
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val newsRepo: NewsRepository,
    private val userPrefRepo: UserPrefRepository
) : ViewModel() {

    var uiMessage by mutableStateOf("")

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

    val announcements: StateFlow<List<News>> = newsRepo.observeNews()
        .catch { e ->
            if (e is FirebaseFirestoreException) {
                when (e.code) {
                    FirebaseFirestoreException.Code.PERMISSION_DENIED -> {
                        uiMessage = "Keine Berechtigung zum Laden der Raclette-Sorten"
                    }
                    FirebaseFirestoreException.Code.UNAVAILABLE -> {
                        uiMessage = "Verbindung zum Server nicht möglich"
                    }
                    else -> {
                        Log.e("FondueViewModel", "Fehler beim Laden: ${e.code.name}" )
                    }
                }
            } else {
                Log.e("FondueViewModel",  "Unbekannter Fehler: ${e.localizedMessage}")
            }
            emit(emptyList())
        }
        .stateIn(
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

