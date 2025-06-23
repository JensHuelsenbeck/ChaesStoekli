package com.example.cheas_stoeckli.data.repositories

import android.net.Uri
import androidx.compose.runtime.MutableState
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


class CloudStorageRepository {

    private val storage = FirebaseStorage.getInstance()

    fun ImageToCloudStorage(
        uri: Uri,
        imageDownloadUrl: MutableState<String>,
        imgPathInCloud: MutableState<String>,
        onFailure: (Exception) -> Unit
    ) {
        try {
            val imageName = "${UUID.randomUUID()}.jpg"
            val imagePath = "images/$imageName"
            val imageRef = storage.reference.child(imagePath)

            imageRef.putFile(uri)
                .addOnSuccessListener {
                    imageRef.downloadUrl
                        .addOnSuccessListener { downloadUrl ->
                            imageDownloadUrl.value = downloadUrl.toString()
                            imgPathInCloud.value = imagePath
                        }
                        .addOnFailureListener(onFailure)
                }
                .addOnFailureListener {
                    when {
                        it.message?.contains("400") == true -> onFailure(CloudUploadError.BadRequest)
                        it.message?.contains("401") == true -> onFailure(CloudUploadError.Unauthorized)
                        it.message?.contains("403") == true -> onFailure(CloudUploadError.Forbidden)
                        it.message?.contains("404") == true -> onFailure(CloudUploadError.NotFound)
                        it.message?.contains("408") == true -> onFailure(CloudUploadError.Timeout)
                        it.message?.contains("500") == true -> onFailure(CloudUploadError.ServerError)
                        else -> onFailure(CloudUploadError.Unknown(it))
                }
                    }
        } catch (e: Exception) {
            onFailure(CloudUploadError.Unknown(e))
        }
    }
    sealed class CloudUploadError : Exception() {
        object BadRequest : CloudUploadError()
        object Unauthorized : CloudUploadError()
        object Forbidden : CloudUploadError()
        object Timeout : CloudUploadError()
        object NotFound : CloudUploadError()
        object ServerError : CloudUploadError()
        data class Unknown(val original: Exception) : CloudUploadError()
    }
}