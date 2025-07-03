package com.example.cheas_stoeckli.data.repositories


import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.OfferMapper
import com.example.cheas_stoeckli.domain.models.FirebaseOffer
import com.example.cheas_stoeckli.domain.models.Offer
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class OfferRepository {

    val cloudStorage = Firebase.storage
    private val db = Firebase.firestore
    private val collection = db.collection("offers")

    private val tag = "OfferRepository: "

    fun observOffers(): Flow<List<Offer>> = callbackFlow {
        val listener = collection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val offerList = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(FirebaseOffer::class.java)
                            ?.let { dto -> OfferMapper.toApp(dto).copy(id = doc.id) }
                    }
                    trySend(offerList).isSuccess
                } else {
                    trySend(emptyList()).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }

    fun deleteOffer(offer: Offer) {
        if (offer.imgPath.isNotEmpty()) {
            cloudStorage.reference.child(offer.imgPath).delete()
                .addOnSuccessListener { Log.i(tag, "Bild in der Cloud wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Bild wurde nicht gelöscht! ${e.message}") }
            collection.document(offer.id).delete()
                .addOnSuccessListener { Log.i(tag, "Annoucement wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Annoucement wurde nicht gelöscht! ${e.message}") }
        } else {
            collection.document(offer.id).delete()
                .addOnSuccessListener { Log.i(tag, "Annoucement wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Annoucement wurde nicht gelöscht! ${e.message}") }
        }
    }

}