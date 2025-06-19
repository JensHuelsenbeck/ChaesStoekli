package com.example.cheas_stoeckli.ui.viewModel

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.domain.usecases.SignInWithGoogleUseCase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.launch

class AuthenticationViewModel(
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val authenticationService: AuthenticationService,
) : ViewModel() {

    fun getGoogleSignInIntent(context: Context): Intent {
        val clientId = getString(context, R.string.web_client_id)
        val signInOptions = getGoogleSignInOptions(clientId)
        val signInClient = GoogleSignIn.getClient(context, signInOptions)
        return signInClient.signInIntent
    }

    private fun getGoogleSignInOptions(serverClientId: String): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(serverClientId)
            .requestId()
            .requestEmail()
            .requestProfile()
            .build()
    }

    fun onGoogleSignInTokenReceive(token: String) {
        viewModelScope.launch {
            signInWithGoogleUseCase(token)
        }
    }
    fun signInAnonymously() {
        authenticationService.createAnonymousAccount()
    }

}