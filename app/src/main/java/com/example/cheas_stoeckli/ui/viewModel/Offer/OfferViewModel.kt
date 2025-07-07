package com.example.cheas_stoeckli.ui.viewModel.Offer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Offer.OfferRepository
import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class OfferViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
  private val offerRepo: OfferRepository
): ViewModel() {

    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val offers: StateFlow<List<Offer>> = offerRepo.observOffers().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun deleteOffer(offer: Offer) {
        offerRepo.deleteOffer(offer)

    }

}