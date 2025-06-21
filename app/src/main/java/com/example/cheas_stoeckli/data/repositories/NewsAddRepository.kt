package com.example.cheas_stoeckli.data.repositories

import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.NewsMapper
import com.example.cheas_stoeckli.domain.models.News
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewsAddRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("announcements")


    fun addAnnoucement(news: News) {
        val firebaseNews = NewsMapper.toFirebase(news)
        collection.document(firebaseNews.id).set(firebaseNews)
            .addOnSuccessListener { Log.d("NewsAddRepository", "Annoucement added successfully") }
            .addOnFailureListener { e ->
                Log.e("NewsAddRepository", "Error writing document: ${e.message}")
            }
    }

}