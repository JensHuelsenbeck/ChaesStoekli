package com.example.cheas_stoeckli.data.repositories.Favorites

import androidx.lifecycle.ViewModel
import com.example.cheas_stoeckli.domain.mappers.CheeseMapper
import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.domain.models.FirebaseCheese
import com.example.cheas_stoeckli.domain.models.User
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FavoriteRacletteRepository(
) : ViewModel() {


    private val db = Firebase.firestore
    private val collection = db.collection("cheese")
    private val favCollection = db.collection("users")


    fun observeFavoRaclette(user: User): Flow<List<Cheese>> = callbackFlow {
        val favRaclette = user.favoriteRacletteIds
        val listener = collection
            .whereIn(FieldPath.documentId(), favRaclette.takeIf { it.isNotEmpty() } ?: listOf("__none__"))
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

    fun addRacletteToFavorites(userId: String, racletteId: String, onResult: (Boolean) -> Unit) {
        favCollection.document(userId)
            .update("favoriteRacletteIds", FieldValue.arrayUnion(racletteId))
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }




}