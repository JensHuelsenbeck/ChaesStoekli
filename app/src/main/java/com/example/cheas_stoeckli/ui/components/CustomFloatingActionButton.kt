package com.example.cheas_stoeckli.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cheas_stoeckli.app.R
import com.example.cheas_stoeckli.ui.theme.loginButtonColor

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.FloatingActionButton(
        onClick = { onClick() },
        containerColor = loginButtonColor,
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 28.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.write_square_24),
            contentDescription = "Beitrag schreiben"
        )
    }
}