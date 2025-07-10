package com.example.cheas_stoeckli

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.ui.screens.LoginScreen
import com.example.cheas_stoeckli.ui.theme.Cheas_StoeckliTheme
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity(
) {
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = screenBackgroundPrimary.toArgb()
        window.navigationBarColor = screenBackgroundPrimary.toArgb()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        enableEdgeToEdge()

        setContent {
            val viewModel: SplashScreenViewModel = koinViewModel()
            val authService = AuthenticationService()
            val isSignedIn by authService.isSignedIn.collectAsState(false)
            Cheas_StoeckliTheme {
                if (viewModel.showSplash) {
                    SplashScreen(onFinished = { viewModel.dismissSplash() })
                } else {
                    (if (isSignedIn) AppStart() else LoginScreen())

                }
            }
        }
    }
}


