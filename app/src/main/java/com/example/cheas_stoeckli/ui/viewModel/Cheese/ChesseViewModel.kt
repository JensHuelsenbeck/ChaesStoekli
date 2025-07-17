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
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
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


    val cheese = cheeseRepo.observeCheese()
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
                        Log.e("CheeseViewModel", "Fehler beim Laden: ${e.code.name}" )
                    }
                }
            } else {
                Log.e("CheeseViewModel",  "Unbekannter Fehler: ${e.localizedMessage}")
            }
            emit(emptyList())
        }
        .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val filteredCheese = combine(
        cheese, milkType, showFavored, favoriteCheeseIds
    ) {
            cheeseList, selectedType, onlyFavorites, favoredIds ->

        val milkFiltered = if (selectedType == MilkType.ALL) {
            cheeseList
        } else {
            cheeseList.filter { it.milkType == selectedType }
        }

        if (onlyFavorites) {
            milkFiltered.filter { it.id in favoredIds }
        } else {
            milkFiltered
        }
    }.stateIn(
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
                            if (success) "Käse zu Favoriten hinzugefügt!" else "Fehler beim Speichern."
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


