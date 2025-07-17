package com.example.cheas_stoeckli.data.repositories

import com.example.cheas_stoeckli.domain.models.User
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("users")

    suspend fun saveUser(user: User): Result<Unit> {
        return try {
            val userRef = collection.document(user.id!!)
            val snapshot = userRef.get().await()

            val data = mutableMapOf<String, Any>(
                "email" to user.email,
                "fullName" to user.fullName
            )
            if (!snapshot.exists()) {
                data["permissionLevel"] = "0"
                data["favoriteFondueIds"] = emptyList<String>()
                data["favoriteCheeseIds"] = emptyList<String>()
                data["favoriteRacletteIds"] = emptyList<String>()
            }
            userRef.set(data, SetOptions.merge()).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun observeUser(id: String): Flow<User?> = callbackFlow {
        val listenerRegistration = collection.document(id).addSnapshotListener { documentSnapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val maybeUser = documentSnapshot?.toObject(User::class.java)
            trySend(maybeUser).isSuccess
        }

        awaitClose {
            listenerRegistration.remove()
        }
    }
}