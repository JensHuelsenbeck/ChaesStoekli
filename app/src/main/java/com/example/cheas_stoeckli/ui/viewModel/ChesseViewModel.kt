package com.example.cheas_stoeckli.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.CheeseRepository
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import com.example.cheas_stoeckli.ui.enums.MilkType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class CheeseViewModel(
    observeCurrentUserUseCase: ObserveCurrentUserUseCase,
    private val cheeseRepo: CheeseRepository
) : ViewModel() {

    private val _type = MutableStateFlow(MilkType.ALL)
    val milkType = _type.asStateFlow()


    val appUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null,
    )

    val cheese = cheeseRepo.observCheese().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val filteredCheese = cheese
        .combine(milkType) { list, selected ->
            if (selected == MilkType.ALL) list
            else list.filter { it.milkType == selected }
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

}

