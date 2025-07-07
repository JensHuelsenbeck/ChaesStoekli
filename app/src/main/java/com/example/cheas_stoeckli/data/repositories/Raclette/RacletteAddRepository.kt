package com.example.cheas_stoeckli.data.repositories.Raclette

import android.util.Log
import com.example.cheas_stoeckli.domain.models.Raclette
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RacletteAddRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("raclette")


    fun addRaclette(
        raclette: Raclette,
        onSuccess: () -> Unit,

        ) {
        collection.document(raclette.id).set(raclette)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("RacletteAddRepository", "Fehler beim schreiben: ${e.message}")

            }
    }

}