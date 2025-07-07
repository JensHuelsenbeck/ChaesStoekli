package com.example.cheas_stoeckli.ui.viewModel.Raclette

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Raclette.RacletteRepository
import com.example.cheas_stoeckli.domain.models.Raclette
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class RacletteViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
  private val racletteRepo: RacletteRepository
): ViewModel() {

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

    fun deleteRaclette(raclette: Raclette) {
        racletteRepo.deleteRaclette(raclette)

    }

}