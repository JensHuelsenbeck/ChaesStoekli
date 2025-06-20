package com.example.cheas_stoeckli.ui.components.Dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.cheas_stoeckli.ui.enums.NewsKind
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NewsAddViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsAddDialog(
    viewModel: NewsAddViewModel = koinViewModel(),
    onDissmiss: () -> Unit,
    modifier: Modifier = Modifier
) {

    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var img by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(NewsKind.NEWS) }


    Dialog(
        onDismissRequest = onDissmiss,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(cardBackgroundPrimary, RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                LazyRow(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(NewsKind.entries) { entry ->

                        EnumItem(
                            enum = entry,
                            onClick = { type = entry },
                        )
                    }
                }
            }
        }
    }
}


