package com.example.cheas_stoeckli.data.repositories


import com.example.cheas_stoeckli.domain.models.News
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NewsRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("announcements")

    fun observeNews(): Flow<List<News>> = callbackFlow {
        val listener = collection
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val items: List<News> = snapshot.documents.mapNotNull { doc ->
                        doc.toObject<News>()?.copy(id = doc.id)
                    }
                    trySend(items).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }
}