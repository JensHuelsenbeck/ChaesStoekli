package com.example.cheas_stoeckli.ui.viewModel.Offer

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.Offer.OfferAddRepository
import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.ui.enums.OfferKind
import kotlinx.coroutines.launch


class OfferAddViewModel(
    private val offerAddRepo: OfferAddRepository,
    private val cloudRepo: CloudStorageRepository
) : ViewModel() {

    var title = mutableStateOf("")
    var text = mutableStateOf("")
    var bottomText = mutableStateOf("")
    var type by mutableStateOf<OfferKind?>(null)
    var imageDownloadUrl = mutableStateOf("")
    var imagePathInsideCloud = mutableStateOf("")
    var imageUri = mutableStateOf<Uri?>(null)
    var isUploading = mutableStateOf(false)

    var errorMessage by mutableStateOf("")

    val previewOffer: Offer
        get() = Offer(
            title = title.value,
            text = text.value,
            bottomText = bottomText.value,
            imgDownloadPath = imageDownloadUrl.value,
            imgPath = imagePathInsideCloud.value,
            type = type ?: OfferKind.ALLGEMEIN,
        )

    fun uploadImageAndSaveOffer(
        onSuccess: () -> Unit,
        ) {
        viewModelScope.launch {
            try {
                if(imageUri.value != null) {
                    val (downloadUrl, imagePath) = cloudRepo.imageToCloudStorage(imageUri.value!!)
                    val offer = Offer(
                        title = title.value,
                        text = text.value,
                        type = type ?: OfferKind.ALLGEMEIN,
                        imgDownloadPath = downloadUrl,
                        imgPath = imagePath
                    )
                    offerAddRepo.addOffer(offer, onSuccess)
                } else {
                    val offer = Offer(
                        title = title.value,
                        text = text.value,
                        type = type ?: OfferKind.ALLGEMEIN,
                        imgDownloadPath = "",
                        imgPath = ""
                    )
                    offerAddRepo.addOffer(offer, onSuccess)
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

        title.value = ""
        text.value = ""
        bottomText.value = ""
        type = null
        imageDownloadUrl.value = ""
        imagePathInsideCloud.value = ""
        imageUri.value = null
    }
}