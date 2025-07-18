package com.example.cheas_stoeckli.ui.components.More

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImpressumSheet(
    onDismiss: () -> Unit,
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        containerColor = cardBackgroundPrimary,
        sheetState = sheetState,
        scrimColor = Color.Black.copy(alpha = 0.8f)
            ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = cardBackgroundPrimary,
            modifier = Modifier
                .fillMaxSize(0.9f)
        ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                    ) {
                    Text(
                        text = """ 
                            Impressum

                            Chäs Stöckli AG  
                            Zürichstrasse 112  
                            8910 Affoltern am Albis  
                            Schweiz  

                            Telefon: +41 44 123 45 67  
                            E-Mail: info@chaes-stoeckli.ch  
                            Handelsregister: CHE-108.080.500  
                            CH-ID: CH-020-3003820-2  
                            Vertretungsberechtigt: Michael Stöckli 
                        """.trimIndent(),
                        color = Color.Black,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
        }
        }
    }