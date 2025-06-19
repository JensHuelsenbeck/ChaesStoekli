package com.example.cheas_stoeckli.domain.usecases

import android.util.Log
import com.example.cheas_stoeckli.data.repositories.UserRepository
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.domain.mappers.FirebaseAuthUserMapper


class SignInWithGoogleUseCase(
    private val authenticationService: AuthenticationService,
    private val userRepository: UserRepository
) {

    companion object {
        const val TAG = "SignInWithGoogleUseCase"
    }

    suspend operator fun invoke(authToken: String)  {
        Log.i(TAG, "invoke: google sign-in with authToken=$authToken")

        val firebaseGoogleUser = authenticationService.signInWithGoogle(authToken)
        requireNotNull(firebaseGoogleUser) { "The user should not be null" }

        val user = FirebaseAuthUserMapper.toDomain(firebaseGoogleUser)
        userRepository.saveUser(user)
    }
}