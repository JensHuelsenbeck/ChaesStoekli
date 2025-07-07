package com.example.cheas_stoeckli.data.repositories.Raclette

import android.util.Log
import com.example.cheas_stoeckli.domain.models.Raclette
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RacletteRepository {


    private val cloudStorage = Firebase.storage
    private val db = Firebase.firestore
    private val collection = db.collection("raclette")

    private val tag = "RacletteRepository: "

    fun observRaclette(): Flow<List<Raclette>> = callbackFlow {
        val listener = collection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }
                if (snapshot != null && !snapshot.isEmpty) {
                    val racletteList = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(Raclette::class.java)
                    }
                    trySend(racletteList).isSuccess
                } else {
                    trySend(emptyList()).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }

    fun deleteRaclette(raclette: Raclette) {
        if (raclette.imgPath.isNotEmpty()) {
            cloudStorage.reference.child(raclette.imgPath).delete()
                .addOnSuccessListener { Log.i(tag, "Bild in der Cloud wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Bild wurde nicht gelöscht! ${e.message}") }
            collection.document(raclette.id).delete()
                .addOnSuccessListener { Log.i(tag, "Annoucement wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Annoucement wurde nicht gelöscht! ${e.message}") }
        } else {
            collection.document(raclette.id).delete()
                .addOnSuccessListener { Log.i(tag, "Annoucement wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Annoucement wurde nicht gelöscht! ${e.message}") }
        }
    }

}