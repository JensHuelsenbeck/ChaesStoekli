package com.example.cheas_stoeckli.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.CheeseRepository
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.example.cheas_stoeckli.ui.enums.MilkType
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class CheeseViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    cheeseRepo: CheeseRepository
): ViewModel() {

    var type by mutableStateOf<MilkType?>(null)

    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val cheese = cheeseRepo.observOffers().stateIn(
        scope = viewModelScope ,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )


}


