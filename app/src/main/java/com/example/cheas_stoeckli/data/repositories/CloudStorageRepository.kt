package com.example.cheas_stoeckli.data.repositories

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import java.util.UUID


class CloudStorageRepository {

    private val storage = FirebaseStorage.getInstance()

    suspend fun imageToCloudStorage(uri: Uri, ): Pair<String, String> {
        val imageName = "${UUID.randomUUID()}.jpg"
        val imagePath = "images/$imageName"
        val imageRef = storage.reference.child(imagePath)

        imageRef.putFile(uri).await()
        val downloadUrl = imageRef.downloadUrl.await()

        return Pair(downloadUrl.toString(), imagePath)
    }
}
