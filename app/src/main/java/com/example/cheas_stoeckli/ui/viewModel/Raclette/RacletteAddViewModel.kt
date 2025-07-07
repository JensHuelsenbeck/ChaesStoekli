package com.example.cheas_stoeckli.ui.viewModel.Raclette

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.Raclette.RacletteAddRepository
import com.example.cheas_stoeckli.domain.models.Raclette
import kotlinx.coroutines.launch


class RacletteAddViewModel(
    private val racletteAddRepo: RacletteAddRepository,
    private val cloudRepo: CloudStorageRepository
) : ViewModel() {

    var name = mutableStateOf("")
    var description = mutableStateOf("")
    var imageDownloadUrl = mutableStateOf("")
    var imagePathInsideCloud = mutableStateOf("")
    var imageUri = mutableStateOf<Uri?>(null)
    var isUploading = mutableStateOf(false)

    var errorMessage by mutableStateOf("")

    val previewRaclette: Raclette
        get() = Raclette(
            name = name.value,
            description = description.value,
            imgDownloadPath = "",
            imgPath = "",
        )

    fun uploadImageAndSaveRaclette(
        onSuccess: () -> Unit,
        ) {
        viewModelScope.launch {
            try {
                if(imageUri.value != null) {
                    val (downloadUrl, imagePath) = cloudRepo.imageToCloudStorage(imageUri.value!!)
                    val raclette = Raclette(
                        name = name.value,
                        description = description.value,
                        imgDownloadPath = downloadUrl,
                        imgPath = imagePath,

                    )
                    racletteAddRepo.addRaclette(raclette, onSuccess)
                } else {
                    val raclette = Raclette(
                        name = name.value,
                        description = description.value,
                        imgDownloadPath = "",
                        imgPath = "",

                        )
                    racletteAddRepo.addRaclette(raclette, onSuccess)
                }


            } catch (e: Exception) {
                errorMessage = "Fehler beim Hochladen oder Speichern"
                Log.e("NewsSave", e.message ?: "")
            } finally {
                isUploading.value = false
                setValuablesToEmpty()
            }
        }
    }

    fun setValuablesToEmpty() {

        name.value = ""
        description.value = ""
        imageDownloadUrl.value = ""
        imagePathInsideCloud.value = ""
        imageUri.value = null
    }
}