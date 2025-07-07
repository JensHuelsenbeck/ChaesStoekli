package com.example.cheas_stoeckli.data.repositories.Fondue

import android.util.Log
import com.example.cheas_stoeckli.domain.models.Fondue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FondueAddRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("fondue")


    fun addFondue(
        fondue: Fondue,
        onSuccess: () -> Unit,

        ) {
        collection.document(fondue.id).set(fondue)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("FondueAddRepository", "Fehler beim schreiben: ${e.message}")

            }
    }

}