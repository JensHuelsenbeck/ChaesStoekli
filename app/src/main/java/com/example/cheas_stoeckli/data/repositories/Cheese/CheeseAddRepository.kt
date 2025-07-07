package com.example.cheas_stoeckli.data.repositories.Cheese

import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.CheeseMapper
import com.example.cheas_stoeckli.domain.models.Cheese
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CheeseAddRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("cheese")


    fun addCheese(
        cheese: Cheese,
        onSuccess: () -> Unit,

        ) {
        val firebaseCheese = CheeseMapper.toFirebase(cheese)
        collection.document(firebaseCheese.id).set(firebaseCheese)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("CheeseAddRepository", "Fehler beim schreiben: ${e.message}")

            }
    }

}