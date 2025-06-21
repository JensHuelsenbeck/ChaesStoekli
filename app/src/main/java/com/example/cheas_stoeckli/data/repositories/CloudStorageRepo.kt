package com.example.cheas_stoeckli.data.repositories

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


class CloudStorageRepo {

    private val storage = FirebaseStorage.getInstance()

    fun uploadImageToFirebase(
        uri: Uri,
        onSuccess: (String, String) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val imageName = "${UUID.randomUUID()}.jpg"
        val imagePath = "images/$imageName"
        val imageRef = storage.reference.child(imagePath)

        imageRef.putFile(uri)
            .addOnSuccessListener {
                imageRef.downloadUrl
                    .addOnSuccessListener { downloadUrl ->
                        onSuccess(downloadUrl.toString(), imagePath)
                    }
                    .addOnFailureListener(onFailure)
            }
            .addOnFailureListener(onFailure)
    }
}