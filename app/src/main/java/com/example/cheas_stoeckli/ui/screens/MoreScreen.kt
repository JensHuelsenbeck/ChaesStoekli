package com.example.cheas_stoeckli.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cheas_stoeckli.ui.components.CustomButton
import com.example.cheas_stoeckli.ui.components.Header
import com.example.cheas_stoeckli.ui.components.More.ContactCard
import com.example.cheas_stoeckli.ui.components.More.DSGVODialog
import com.example.cheas_stoeckli.ui.components.More.ImpressumSheet
import com.example.cheas_stoeckli.ui.components.More.OpeningCard
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary

@Composable
fun MoreScreen(modifier: Modifier = Modifier) {

    val isImpressumShown = remember { mutableStateOf(false) }
    val isDSGVOShown = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = screenBackgroundPrimary
    ) {
        Box() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Header("Öffnungszeiten")
                }
                OpeningCard()
                Spacer(Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Header("Kontakt")
                }
                ContactCard()
                Spacer(Modifier.height(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
                    CustomButton(
                        text = "Impressum",
                        onClick = { isImpressumShown.value = true },

                    )
                    Spacer(Modifier.height(8.dp))
                    CustomButton(
                        text = "Datenschutz",
                        onClick = { isDSGVOShown.value = true },
                    )
                }
            }
        }
    }
    if(isImpressumShown.value) {
        ImpressumSheet(onDismiss = { isImpressumShown.value = false })
    }
    if(isDSGVOShown.value) {
        DSGVODialog(onDismiss = { isDSGVOShown.value = false })
    }
}

