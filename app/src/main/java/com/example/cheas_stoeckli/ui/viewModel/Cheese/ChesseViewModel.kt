package com.example.cheas_stoeckli.ui.viewModel.Cheese

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Cheese.CheeseRepository
import com.example.cheas_stoeckli.data.repositories.Favorites.FavoriteCheeseRepository
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.domain.models.User
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.example.cheas_stoeckli.ui.enums.MilkType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CheeseViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val cheeseRepo: CheeseRepository,
    private val favoriteCheeseRepo: FavoriteCheeseRepository
) : ViewModel() {

    private val _type = MutableStateFlow(MilkType.ALL)
    val milkType = _type.asStateFlow()

    private val _showFavored = MutableStateFlow(false)
    val showFavored = _showFavored.asStateFlow()

    private val _favoriteCheeseIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteCheeseIds = _favoriteCheeseIds.asStateFlow()


    var uiMessage by mutableStateOf("")


    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )


    val cheese = cheeseRepo.observeCheese().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val filteredCheese = cheese
        .combine(milkType) { list, selectedType ->
            if (selectedType == MilkType.ALL) list
            else list.filter { it.milkType == selectedType }
        }
        .combine(showFavored) { list, onlyFavorites ->
            if (onlyFavorites) {
                val favoredIds = favoriteCheeseIds.value
                list.filter { it.id in favoredIds }
            } else list
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun setMilkType(type: MilkType) {
        _type.value = type
        Log.d("CheeseViewModel", "MilkType is now: ${_type.value}")
    }

    fun deleteCheese(cheese: Cheese) {
        cheeseRepo.deleteCheese(cheese)

    }


    init {
        viewModelScope.launch {
            appUser.collect { user ->
                _favoriteCheeseIds.value = user?.favoriteCheeseIds
                    ?.map { it.id }
                    ?.toSet() ?: emptySet()
            }
        }
    }

    fun addCheeseToFavorites(cheeseId: String) {
        viewModelScope.launch {
            try {
                favoriteCheeseRepo.addCheeseToFavorites(
                    userId = appUser.value?.id ?: "",
                    cheeseId = cheeseId,
                    onResult = { success ->
                        uiMessage =
                            if (success) "Zu Favoriten hinzugefügt!" else "Fehler beim Speichern."
                    }
                )
            } catch (e: Exception) {
                uiMessage = "Ein Fehler ist aufgetreten."
            }
        }
    }

    fun deleteCheeseFromFavorites(cheeseId: String) {
        viewModelScope.launch {
            try {
                favoriteCheeseRepo.deleteCheeseFromFavorites(
                    user = appUser.value ?: User(),
                    cheeseId = cheeseId,
                    onResult = { success ->
                        uiMessage =
                            if (success) "Käse aus Favoriten entfernt!" else "Fehler beim Entfernen."
                    }
                )

            } catch (e: Exception) {
                uiMessage = "Ein Fehler ist aufgetreten."
                Log.e("CheeseViewModel", "Error deleting cheese from favorites, ${e.message}")
            }
        }
    }

    fun setShowFavoredToTrue() {
        _showFavored.value = true
        Log.d("CheeseViewModel", "ShowFavored is now: ${_showFavored.value}")
    }
    fun setShowFavoredToFalse() {
        _showFavored.value = false
        Log.d("CheeseViewModel", "ShowFavored is now: ${_showFavored.value}")

    }

}


