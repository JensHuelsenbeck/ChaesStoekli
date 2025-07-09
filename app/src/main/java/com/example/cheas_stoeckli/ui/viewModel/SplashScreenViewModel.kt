package com.example.cheas_stoeckli.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SplashScreenViewModel(): ViewModel() {

    var showSplash by  mutableStateOf(true)

    fun dismissSplash() {
        showSplash = false
    }
}