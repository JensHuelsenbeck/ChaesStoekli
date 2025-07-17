package com.example.cheas_stoeckli.ui.viewModel.Offer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Offer.OfferRepository
import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn

class OfferViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
  private val offerRepo: OfferRepository
): ViewModel() {

    var uiMessage by mutableStateOf("")

    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val offers: StateFlow<List<Offer>> = offerRepo.observeOffers()
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

    fun deleteOffer(offer: Offer) {
        offerRepo.deleteOffer(offer)

    }

}