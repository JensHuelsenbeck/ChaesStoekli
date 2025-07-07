package com.example.cheas_stoeckli.data.repositories.Offer

import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.OfferMapper
import com.example.cheas_stoeckli.domain.models.Offer
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class OfferAddRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("offers")


    fun addOffer(
        offer: Offer,
        onSuccess: () -> Unit,

        ) {
        val firebaseNews = OfferMapper.toFirebase(offer)
        collection.document(firebaseNews.id).set(firebaseNews)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("OfferAddRepository", "Fehler beim schreiben: ${e.message}")

            }
    }

}