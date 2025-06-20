package com.example.cheas_stoeckli

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.cheas_stoeckli.ui.components.Dialog.NewsAddDialog
import com.example.cheas_stoeckli.ui.theme.Cheas_StoeckliTheme
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = screenBackgroundPrimary.toArgb()
        window.navigationBarColor = screenBackgroundPrimary.toArgb()
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
        enableEdgeToEdge()

        setContent {
            Cheas_StoeckliTheme {
                NewsAddDialog()
                }
            }
        }
    }


