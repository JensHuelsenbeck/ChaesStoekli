package com.example.cheas_stoeckli.ui.viewModel.Fondue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.Fondue.FondueRepository
import com.example.cheas_stoeckli.domain.models.Fondue
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class FondueViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val fondueRepo: FondueRepository
) : ViewModel() {


    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val fondue = fondueRepo.observFondue().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun deleteFondue(fondue: Fondue) {
        fondueRepo.deleteFondue(fondue)

    }

}

