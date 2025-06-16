package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.components.LoginButtons.GuestLoginButton
import com.example.cheas_stoeckli.ui.components.LoginButtons.LoginButton

@Composable
fun LoginScreen(

) {

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val logoSize = screenHeight * 0.15f

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background((Color.Black))
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(30.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Seit 1965 in Affoltern am Albis",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f)
                    .clipToBounds()
            ) {
                Image(
                    painter = painterResource(R.drawable.loginbackground),
                    contentDescription = "LoginBackground",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                        .clipToBounds()
                )
                Image(
                    painter = painterResource(R.drawable.login_name),
                    contentDescription = "Login Logo",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(bottom = 475.dp)
                        .height(80.dp)
                        .fillMaxWidth(0.9f)
                )
                Spacer(Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 100.dp)
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Herzlich Willkommen bei Chäs Stöckli",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    LoginButton(
                        onClick = {},
                    )
                }
            }
            Spacer(Modifier.height(8.dp))

                GuestLoginButton(
                    onClick = {}
                )

        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}