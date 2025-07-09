package com.example.cheas_stoeckli

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onFinished: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(screenBackgroundPrimary),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.splash_image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
    LaunchedEffect(Unit) {
        delay(5000)
        onFinished()
    }
}