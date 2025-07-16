package com.example.cheas_stoeckli.data.repositories.Favorites

import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.domain.models.FavoriteFondue
import com.example.cheas_stoeckli.domain.models.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoriteFondueRepository(
) : ViewModel() {


    private val db = Firebase.firestore
    private val favCollection = db.collection("users")



    fun addFondueToFavorites(userId: String, fondueId: String, onResult: (Boolean) -> Unit) {
        val entry = FavoriteFondue(
            id = fondueId,
            createdAt = Timestamp.now())
        favCollection.document(userId)
            .update("favoriteFondueIds", FieldValue.arrayUnion(entry))
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }
    fun deleteFondueFromFavorites(user: User, fondueId: String, onResult: (Boolean) -> Unit) {
        val entryToRemove = user.favoriteFondueIds.find { it.id == fondueId }

        favCollection.document(user.id ?: "")
            .update("favoriteFondueIds", FieldValue.arrayRemove(entryToRemove))
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }




}