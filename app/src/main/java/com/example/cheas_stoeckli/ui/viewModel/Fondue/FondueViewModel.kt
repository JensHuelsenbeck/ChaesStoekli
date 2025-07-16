package com.example.cheas_stoeckli.ui.viewModel.Fondue

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Favorites.FavoriteFondueRepository
import com.example.cheas_stoeckli.data.repositories.Fondue.FondueRepository
import com.example.cheas_stoeckli.domain.models.Fondue
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FondueViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val fondueRepo: FondueRepository,
    private val favoriteFondueRepo: FavoriteFondueRepository
) : ViewModel() {


    private val _showFavored = MutableStateFlow(false)
    val showFavored = _showFavored.asStateFlow()

    private val _favoriteFondueIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteFondueIds = _favoriteFondueIds.asStateFlow()


    var uiMessage by mutableStateOf("")


    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val fondue = fondueRepo.observeFondue().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val filteredFondue: StateFlow<List<Fondue>> = combine(
        fondue, favoriteFondueIds, showFavored
    ) { fondueList, favIds, onlyFavorites ->
        if (onlyFavorites) {
            fondueList.filter { it.id in favIds }
        } else {
            fondueList
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun deleteFondue(fondue: Fondue) {
        fondueRepo.deleteFondue(fondue)

    }

    init {
        viewModelScope.launch {
            appUser.collect { user ->
                _favoriteFondueIds.value = user?.favoriteFondueIds
                    ?.map { it.id }
                    ?.toSet() ?: emptySet()
            }
        }
    }
    fun addFondueToFavorites(fondueId: String) {
        viewModelScope.launch {
            try {
                favoriteFondueRepo.addFondueToFavorites(
                    userId = appUser.value?.id ?: "",
                    fondueId = fondueId,
                    onResult = { success ->
                        uiMessage =
                            if (success) "Fonduemischung zu Favoriten hinzugefügt!" else "Fehler beim Speichern."
                    }
                )
            } catch (e: Exception) {
                uiMessage = "Ein Fehler ist aufgetreten."
            }
        }
    }

    fun deleteFondueFromFavorites(fondueId: String) {
        viewModelScope.launch {
            try {
                favoriteFondueRepo.deleteFondueFromFavorites(
                    user = appUser.value ?: User(),
                    fondueId = fondueId,
                    onResult = { success ->
                        uiMessage =
                            if (success) "Fonduemischung aus Favoriten entfernt!" else "Fehler beim Entfernen."
                    }
                )

            } catch (e: Exception) {
                uiMessage = "Ein Fehler ist aufgetreten."
                Log.e("FondueViewModel", "Error deleting fondue from favorites, ${e.message}")
            }
        }
    }
    fun setShowFavoredToTrue() {
        _showFavored.value = true
        Log.d("FondueViewModel", "ShowFavored is now: ${_showFavored.value}")
    }
    fun setShowFavoredToFalse() {
        _showFavored.value = false
        Log.d("FondueViewModel", "ShowFavored is now: ${_showFavored.value}")

    }


}

