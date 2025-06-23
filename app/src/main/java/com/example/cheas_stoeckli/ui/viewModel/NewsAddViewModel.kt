package com.example.cheas_stoeckli.ui.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository
import com.example.cheas_stoeckli.data.repositories.CloudStorageRepository.CloudUploadError
import com.example.cheas_stoeckli.data.repositories.NewsAddRepository
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind


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

    fun addNews(
        title: String,
        text: String,
        destination: String,
        date: String,
        time: String,
        type: NewsKind,

        ) {
        val news = News(
            title = title,
            text = text,
            imgDownloadPath = imageDownloadUrl.value,
            imgPath = imagePathInsideCloud.value,
            destination = destination,
            date = date,
            time = time,
            type = type,
        )
        newsAddRepo.addAnnoucement(news)
        setValuablesToEmpty()
    }

    fun addPictureToCloud(
        uri: Uri,
    ) {
        cloudRepo.ImageToCloudStorage(
            uri = uri,
            imageDownloadUrl = imageDownloadUrl,
            imgPathInCloud = imagePathInsideCloud,
            onFailure = { exception ->
                val error = when (exception) {
                    is CloudUploadError -> exception
                    else -> CloudUploadError.Unknown(exception)
                }

                when (error) {
                    is CloudUploadError.BadRequest -> Log.e("Upload", "Fehler: BadRequest")
                    is CloudUploadError.Unauthorized -> Log.e("Upload", "Fehler: Unauthorized")
                    is CloudUploadError.Forbidden -> Log.e("Upload", "Fehler: Forbidden")
                    is CloudUploadError.NotFound -> Log.e("Upload", "Fehler: NotFound")
                    is CloudUploadError.Timeout -> Log.e("Upload", "Fehler: Timeout")
                    is CloudUploadError.ServerError -> Log.e("Upload", "Fehler: ServerError")
                    is CloudUploadError.Unknown -> Log.e(
                        "Upload",
                        "Fehler: Unknown: ${error.original.message}"
                    )
                }
            }
        )
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

    }

}



