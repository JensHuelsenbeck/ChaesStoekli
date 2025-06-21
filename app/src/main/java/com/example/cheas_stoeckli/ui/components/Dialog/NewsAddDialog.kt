package com.example.cheas_stoeckli.ui.components.Dialog

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.cheas_stoeckli.ui.enums.NewsKind
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NewsAddViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NewsAddDialog(
    viewModel: NewsAddViewModel = koinViewModel(),
    isDialogOpen: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        // Hier passiert aktuell nichts
    }




    var title = remember { mutableStateOf("") }
    var text = remember { mutableStateOf("") }
    var img = remember { mutableStateOf("") }
    var imagePath = remember { mutableStateOf("") }
    var destination = remember { mutableStateOf("") }
    var date = remember { mutableStateOf("") }
    var time = remember { mutableStateOf("") }
    var type by remember { mutableStateOf<NewsKind?>(null) }


    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var isSelected by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = {},
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .background(cardBackgroundPrimary, RoundedCornerShape(12.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.background(cardBackgroundPrimary)

                    .fillMaxSize()
            ) {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Neue Ankündigung",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black

                )
                Spacer(Modifier.height(20.dp))
                LazyRow(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(NewsKind.entries) { entry ->
                        EnumItem(
                            enum = entry,
                            onClick = { type = entry },
                            isSelected = type == entry
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                DialogTextField(
                    usedString = title ,
                    label = "Überschrift",
                    placeholder = "Wähle eine kurze Überschrift",
                    maxLines = 2,
                    minLines = 2
                )
                DialogTextField(
                    usedString = text ,
                    label = "Beschreibung",
                    placeholder = "Erzähle worum es geht",
                    maxLines = 5,
                    minLines = 5
                )
                if(type == NewsKind.EVENTS || type == NewsKind.REMINDER) {
                    DialogTextField(
                        usedString = date,
                        label = "Datum",
                        placeholder = "Für Veranstaltungen oder Erinnerungen, zB. 05.12.25 oder Di, 20.05",
                        maxLines = 2,
                        minLines = 2
                    )
                    DialogTextField(
                        usedString = time,
                        label = "Uhrzeit",
                        placeholder = "Schreibe hier die Uhrzeit hinein? zb. 14:30 ",
                        maxLines = 2,
                        minLines = 2
                    )
                }
                if(type == NewsKind.EVENTS) {
                    DialogTextField(
                        usedString = destination,
                        label = "Veranstaltungsort",
                        placeholder = "Schreibe die die Adresse hinein, zb Zürichstrasse 106, 8910 Affoltern am Albis",
                        maxLines = 2,
                        minLines = 2
                    )
                }
                Spacer(Modifier.height(4.dp))
                AddPictureButton(
                    img = img,
                    imagePath = imagePath,
                    onClickAddPicture = { launcher.launch("image/*") }
                )
                Spacer(Modifier.height(8.dp))
                SaveNewsButton(
                    onClickSaveNews = {

                        Log.d("SaveNewsButton", "title: ${title.value}")
                        Log.d("SaveNewsButton", "text: ${text.value}")
                        Log.d("SaveNewsButton", "img: ${img.value}")
                        Log.d("SaveNewsButton", "destination: ${destination.value}")
                        Log.d("SaveNewsButton", "date: ${date.value}")
                        Log.d("SaveNewsButton", "time: ${time.value}")
                        Log.d("SaveNewsButton", "type: ${type?.name ?: "null"}")

                        viewModel.addNews(
                        title = title.value,
                        text = text.value,
                        img = img.value,
                        destination = destination.value,
                        date = date.value,
                        type = type ?: NewsKind.NEWS,
                        time = time.value
                    )

                                      },
                )
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}


