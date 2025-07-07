package com.example.cheas_stoeckli.data.repositories.Cheese

import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.CheeseMapper
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.domain.models.FirebaseCheese
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class CheeseRepository {



    private val db = Firebase.firestore
    private val collection = db.collection("cheese")

    private val tag = "OfferRepository: "

    fun observCheese(): Flow<List<Cheese>> = callbackFlow {
        val listener = collection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val cheeseList = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(FirebaseCheese::class.java)
                            ?.let { dto -> CheeseMapper.toApp(dto).copy(id = doc.id) }
                    }
                    trySend(cheeseList).isSuccess
                } else {
                    trySend(emptyList()).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }

    fun deleteCheese(cheese: Cheese) {
            collection.document(cheese.id).delete()
                .addOnSuccessListener { Log.i(tag, "Annoucement wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Annoucement wurde nicht gelöscht! ${e.message}") }

    }
}