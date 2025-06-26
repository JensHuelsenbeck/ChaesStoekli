package com.example.cheas_stoeckli.ui.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.NewsAddRepository
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind
import kotlinx.coroutines.launch


class NewsAddViewModel(
    private val newsAddRepo: NewsAddRepository,
    private val cloudRepo: CloudStorageRepository
) : ViewModel() {

    var title = mutableStateOf("")
    var text = mutableStateOf("")
    var destination = mutableStateOf("")
    var date = mutableStateOf("")
    var time = mutableStateOf("")
    var type by mutableStateOf<NewsKind?>(null)
    val imageDownloadUrl = mutableStateOf("")
    val imagePathInsideCloud = mutableStateOf("")

    val imageUri = mutableStateOf<Uri?>(null)
    val isUploading = mutableStateOf(false)
    val errorMessage = mutableStateOf("")

    val previewNews: News
        get() = News(
            title = title.value,
            text = text.value,
            destination = destination.value,
            date = date.value,
            time = time.value,
            type = type ?: NewsKind.NEWS,
            imgDownloadPath = imageDownloadUrl.value,
            imgPath = imagePathInsideCloud.value
        )

    fun uploadImageAndSaveNews(

        title: String,
        text: String,
        destination: String,
        date: String,
        time: String,
        type: NewsKind,
        onSuccess: () -> Unit,

        ) {
        viewModelScope.launch {
            try {
                val (downloadUrl, imagePath) = cloudRepo.imageToCloudStorage(imageUri.value!!)
                val news = News(
                    title = title,
                    text = text,
                    destination = destination,
                    date = date,
                    time = time,
                    type = type,
                    imgDownloadPath = downloadUrl,
                    imgPath = imagePath
                )
                newsAddRepo.addAnnoucement(news, onSuccess)

            } catch (e: Exception) {
                errorMessage.value = "Fehler beim Hochladen oder Speichern"
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
        destination.value = ""
        date.value = ""
        time.value = ""
        type = null
        imageDownloadUrl.value = ""
        imagePathInsideCloud.value = ""
        imageUri.value = null
    }
}



