package com.example.cheas_stoeckli.data.repositories.Favorites

import com.example.cheas_stoeckli.domain.mappers.CheeseMapper
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.domain.models.FavoriteCheese
import com.example.cheas_stoeckli.domain.models.FirebaseCheese
import com.example.cheas_stoeckli.domain.models.User
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FavoriteCheeseRepository(
) {
    private val db = Firebase.firestore
    private val collection = db.collection("cheese")
    private val favCollection = db.collection("users")



    fun observeFavoriteCheese(user: User): Flow<List<Cheese>> = callbackFlow {
        val favoriteEntries = user.favoriteCheeseIds
        val sortedIds = favoriteEntries
            .sortedByDescending { it.createdAt }
            .map { it.id }

        val listener = collection
            .whereIn(FieldPath.documentId(), sortedIds.takeIf { it.isNotEmpty() } ?: listOf("__none__"))
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val cheeseList = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(FirebaseCheese::class.java)
                        ?.let { dto -> CheeseMapper.toApp(dto).copy(id = doc.id) }
                } ?: emptyList()

                // Sortiere die Cheese-Objekte anhand der Reihenfolge in sortedIds
                val sortedCheese = sortedIds.mapNotNull { id ->
                    cheeseList.find { it.id == id }
                }

                trySend(sortedCheese).isSuccess
            }

        awaitClose { listener.remove() }
    }

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