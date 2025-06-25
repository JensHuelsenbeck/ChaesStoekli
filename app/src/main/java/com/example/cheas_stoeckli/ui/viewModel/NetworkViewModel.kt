package com.example.cheas_stoeckli.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.utils.ConnectivityObserver
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class NetworkViewModel(
    context: Context
): ViewModel() {

    private val connectivityObserver = ConnectivityObserver(context)

    val isOnline: StateFlow<Boolean> = connectivityObserver.networkStatus
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = true
        )

}