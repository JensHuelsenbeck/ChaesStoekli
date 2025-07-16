package com.example.cheas_stoeckli.data.repositories.Favorites

import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.domain.models.FavoriteRaclette
import com.example.cheas_stoeckli.domain.models.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoriteRacletteRepository(
) : ViewModel() {


    private val db = Firebase.firestore
    private val favCollection = db.collection("users")

    fun addRacletteToFavorites(userId: String, racletteId: String, onResult: (Boolean) -> Unit) {
        val entry = FavoriteRaclette(
            id = racletteId,
            createdAt = Timestamp.now())
        favCollection.document(userId)
            .update("favoriteRacletteIds", FieldValue.arrayUnion(entry))
            .addOnSuccessListener { onResult(true) }.addOnFailureListener { onResult(false) }
    }

    fun deleteRacletteFromFavorites(user: User, racletteId: String, onResult: (Boolean) -> Unit) {
        val entryToRemove = user.favoriteRacletteIds.find { it.id == racletteId }

        favCollection.document(user.id ?: "")
            .update("favoriteRacletteIds", FieldValue.arrayRemove(entryToRemove))
            .addOnSuccessListener { onResult(true) }.addOnFailureListener { onResult(false) }
    }

}