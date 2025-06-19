package com.example.cheas_stoeckli.domain.usecases

import android.util.Log
import com.example.cheas_stoeckli.data.repositories.UserRepository
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.domain.models.User


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf

class ObserveCurrentUserUseCase(
    private val authenticationService: AuthenticationService,
    private val userRepository: UserRepository
) {

    companion object {
        const val TAG = "ObserveCurrentUserUseCase"
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke() : Flow<User?> {
        return authenticationService.userId.flatMapConcat { userId ->
            Log.i(TAG, "invoke: observing user with id=$userId")
            if (userId == null) {
                flowOf(null)
            } else {
                userRepository.observeUser(userId)
            }
        }
    }
}