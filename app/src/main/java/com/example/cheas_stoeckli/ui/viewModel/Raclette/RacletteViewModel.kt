package com.example.cheas_stoeckli.ui.viewModel.Raclette

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Favorites.FavoriteRacletteRepository
import com.example.cheas_stoeckli.data.repositories.Raclette.RacletteRepository
import com.example.cheas_stoeckli.domain.models.Raclette
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RacletteViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
  private val racletteRepo: RacletteRepository,
    private val favoriteRacletteRepo: FavoriteRacletteRepository
): ViewModel() {

    private val _showFavored = MutableStateFlow(false)
    val showFavored = _showFavored.asStateFlow()

    private val _favoriteRacletteIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteRacletteIds = _favoriteRacletteIds.asStateFlow()


    var uiMessage by mutableStateOf("")

    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val raclette: StateFlow<List<Raclette>> = racletteRepo.observRaclette().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val filteredRaclette: StateFlow<List<Raclette>> = combine(
        raclette, favoriteRacletteIds, showFavored
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

    fun deleteRaclette(raclette: Raclette) {
        racletteRepo.deleteRaclette(raclette)

    }
    init {
        viewModelScope.launch {
            appUser.collect { user ->
                _favoriteRacletteIds.value = user?.favoriteRacletteIds
                    ?.map { it.id }
                    ?.toSet() ?: emptySet()
            }
        }
    }
    fun addRacletteToFavorites(fondueId: String) {
        viewModelScope.launch {
            try {
                favoriteRacletteRepo.addRacletteToFavorites(
                    userId = appUser.value?.id ?: "",
                    racletteId = fondueId,
                    onResult = { success ->
                        uiMessage =
                            if (success) "Raclettekäse zu Favoriten hinzugefügt!" else "Fehler beim Speichern."
                    }
                )
            } catch (e: Exception) {
                uiMessage = "Ein Fehler ist aufgetreten."
            }
        }
    }

    fun deleteRacletteFromFavorites(fondueId: String) {
        viewModelScope.launch {
            try {
                favoriteRacletteRepo.deleteRacletteFromFavorites(
                    user = appUser.value ?: User(),
                    racletteId = fondueId,
                    onResult = { success ->
                        uiMessage =
                            if (success) "Raclettekäse aus Favoriten entfernt!" else "Fehler beim Entfernen."
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