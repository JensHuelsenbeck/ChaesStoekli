package com.example.cheas_stoeckli.data.repositories


import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.NewsMapper
import com.example.cheas_stoeckli.domain.models.FirestoreNews
import com.example.cheas_stoeckli.domain.models.News
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NewsRepository {

    val cloudStorage = Firebase.storage
    private val db = Firebase.firestore
    private val collection = db.collection("announcements")

    private val tag = "NewsRepository: "

    fun observeNews(): Flow<List<News>> = callbackFlow {
        val listener = collection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val newsList = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(FirestoreNews::class.java)
                            ?.let { dto -> NewsMapper.toApp(dto).copy(id = doc.id) }
                    }
                    trySend(newsList).isSuccess
                } else {
                    trySend(emptyList()).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }

    fun deleteAnnouncement(news: News) {
        if (news.imgPath.isNotEmpty()) {
            cloudStorage.reference.child(news.imgPath).delete()
                .addOnSuccessListener { Log.i(tag, "Bild in der Cloud wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Bild wurde nicht gelöscht! ${e.message}") }
            collection.document(news.id).delete()
                .addOnSuccessListener { Log.i(tag, "Annoucement wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Annoucement wurde nicht gelöscht! ${e.message}") }
        } else {
            collection.document(news.id).delete()
                .addOnSuccessListener { Log.i(tag, "Annoucement wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Annoucement wurde nicht gelöscht! ${e.message}") }
        }
    }

}