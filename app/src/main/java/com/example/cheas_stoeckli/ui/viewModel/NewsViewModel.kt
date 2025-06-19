package com.example.cheas_stoeckli.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.Fake.newsList
import com.example.cheas_stoeckli.domain.domain.usecases.SignOutUseCase
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

class NewsViewModel(
    private val signOutUseCase: SignOutUseCase,
    observeCurrentUserUseCase: ObserveCurrentUserUseCase
): ViewModel() {

    private val _news = MutableStateFlow(newsList)
    val news = _news.asStateFlow()

    val currentUser = observeCurrentUserUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun onSignOutClick() {
        signOutUseCase()
    }

}