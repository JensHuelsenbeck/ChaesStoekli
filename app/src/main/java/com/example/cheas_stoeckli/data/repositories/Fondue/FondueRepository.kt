package com.example.cheas_stoeckli.data.repositories.Fondue

import android.util.Log
import com.example.cheas_stoeckli.domain.models.Fondue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FondueRepository {



    private val db = Firebase.firestore
    private val collection = db.collection("fondue")

    private val tag = "FondueRepository: "

    fun observeFondue(): Flow<List<Fondue>> = callbackFlow {
        val listener = collection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val fondueList = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(Fondue::class.java)
                    }
                    trySend(fondueList).isSuccess
                } else {
                    trySend(emptyList()).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }

    fun deleteFondue(fondue: Fondue) {
            collection.document(fondue.id).delete()
                .addOnSuccessListener { Log.i(tag, "Fondue wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Fondue wurde nicht gelöscht! ${e.message}") }

    }
}