package com.example.cheas_stoeckli.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.data.Fake.newsList
import com.example.cheas_stoeckli.domain.domain.usecases.SignOutUseCase
import com.example.cheas_stoeckli.domain.usecases.ObserveCurrentUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel(
    private val signOutUseCase: SignOutUseCase,
    observeCurrentUserUseCase: ObserveCurrentUserUseCase
): ViewModel() {

    private val _news = MutableStateFlow(newsList)
    val news = _news.asStateFlow()

    fun onSignOutClick() {
        signOutUseCase()
    }

}

