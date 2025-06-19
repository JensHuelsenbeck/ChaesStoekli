package com.example.cheas_stoeckli.domain.domain.usecases

import android.util.Log
import com.example.cheas_stoeckli.data.services.AuthenticationService


class SignOutUseCase(
    private val authenticationService: AuthenticationService
) {

    companion object {
        const val TAG = "SignOutUseCase"
    }

    operator fun invoke() {
        Log.i(TAG, "invoke: logging out user")
        authenticationService.signOut()
    }
}