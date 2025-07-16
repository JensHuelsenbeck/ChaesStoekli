package com.example.cheas_stoeckli.ui.components.Raclette

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cheas_stoeckli.ui.components.AddNewsDialog.AddPictureButton
import com.example.cheas_stoeckli.ui.components.AddNewsDialog.ConfirmDialog
import com.example.cheas_stoeckli.ui.components.AddNewsDialog.DialogTextField
import com.example.cheas_stoeckli.ui.components.CustomButton
import com.example.cheas_stoeckli.ui.theme.cardBackgroundPrimary
import com.example.cheas_stoeckli.ui.theme.loginButtonColor
import com.example.cheas_stoeckli.ui.viewModel.Raclette.RacletteAddViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RacletteAddDialog(
    viewModel: RacletteAddViewModel = koinViewModel(),
    isDialogOpen: MutableState<Boolean>,
    snackbarHostState: SnackbarHostState,
    snackbarScope: CoroutineScope,
) {


    val errorMessage = viewModel.errorMessage
    val showConfirmDialog = remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.imageUri.value = it
        }
    }

    Dialog(
        onDismissRequest = { showConfirmDialog.value = true },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = cardBackgroundPrimary,
            modifier = Modifier
                .fillMaxSize(0.9f)
        ) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.verticalScroll(rememberScrollState()),

                ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .background(cardBackgroundPrimary)
                        .fillMaxSize()

                ) {
                    Spacer(Modifier.height(10.dp))
                    Row(
                        horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { showConfirmDialog.value = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "",
                                tint = Color.Black,
                                modifier = Modifier
                                    .height(30.dp)
                                    .width(45.dp)
                                    .background(
                                        loginButtonColor.copy(alpha = 0.8f),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .padding(8.dp)
                            )
                        }
                    }
                    Text(
                        text = "Neue Raclettekäse",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(20.dp))
                    DialogTextField(
                        usedString = viewModel.name,
                        label = "Name",
                        placeholder = "Wie heißt die Mischung?",
                        maxLines = 2,
                        minLines = 2
                    )
                    DialogTextField(
                        usedString = viewModel.description,
                        label = "Beschreibung",
                        placeholder = "Erzähle etwas über die Raclettemischung. ",
                        maxLines = 5,
                        minLines = 5
                    )
                    Spacer(Modifier.height(4.dp))
                    AddPictureButton(
                        onClickAddPicture = { launcher.launch("image/*") })
                    Spacer(Modifier.height(8.dp))
                    RacletteCard(
                        raclette = viewModel.previewRaclette,
                        onClickDelete = {},
                        user = null,
                        viewModel = null
                    )
                    Spacer(Modifier.height(8.dp))
                    CustomButton(
                        text = "Speichern",
                        onClick = {
                            viewModel.uploadImageAndSaveRaclette(
                                onSuccess = {
                                    snackbarScope.launch {
                                        snackbarHostState.showSnackbar("Erfolgreich gespeichert!")
                                    }
                                },
                            )
                            isDialogOpen.value = false
                        },

                        )
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
    if (showConfirmDialog.value) {
        ConfirmDialog(
            confirmText = "Zurück zu Raclettesortiment? Dabei werden alle Eingaben verworfen.",
            showConfirmDialog = showConfirmDialog,
            showAddDialog = isDialogOpen,
            onClick = { viewModel.setValuablesToEmpty() }
        )
    }
    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            snackbarHostState.showSnackbar(errorMessage)
            viewModel.errorMessage = ""
        }
    }
}


