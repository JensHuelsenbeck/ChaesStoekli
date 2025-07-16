package com.example.cheas_stoeckli.data.repositories.Favorites

import com.example.cheas_stoeckli.domain.models.FavoriteCheese
import com.example.cheas_stoeckli.domain.models.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoriteCheeseRepository(
) {
    private val db = Firebase.firestore
    private val favCollection = db.collection("users")





    fun addCheeseToFavorites(userId: String, cheeseId: String, onResult: (Boolean) -> Unit) {
        val entry = FavoriteCheese(
            id = cheeseId,
            createdAt = Timestamp.now())
       favCollection.document(userId)
            .update("favoriteCheeseIds", FieldValue.arrayUnion(entry))
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
    fun deleteCheeseFromFavorites(user: User, cheeseId: String, onResult: (Boolean) -> Unit) {
        val entryToRemove = user.favoriteCheeseIds.find { it.id == cheeseId }

        favCollection.document(user.id ?: "")
            .update("favoriteCheeseIds", FieldValue.arrayRemove(entryToRemove))
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }



}