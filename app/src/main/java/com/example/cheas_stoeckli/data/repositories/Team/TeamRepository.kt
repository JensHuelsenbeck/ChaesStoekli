package com.example.cheas_stoeckli.data.repositories.Team


import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.TeamMemberMapper
import com.example.cheas_stoeckli.domain.models.FirebaseTeamMember
import com.example.cheas_stoeckli.domain.models.TeamMember
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class TeamRepository {

    val cloudStorage = Firebase.storage
    private val db = Firebase.firestore
    private val collection = db.collection("teamMember")

    private val tag = "TeamRepository: "

    fun observeTeamMembers(): Flow<List<TeamMember>> = callbackFlow {
        val listener = collection
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val teamMemberList = snapshot.documents.mapNotNull { doc ->
                        doc.toObject(FirebaseTeamMember::class.java)
                            ?.let { dto -> TeamMemberMapper.toApp(dto).copy(id = doc.id) }
                    }
                    trySend(teamMemberList).isSuccess
                } else {
                    trySend(emptyList()).isSuccess
                }
            }
        awaitClose { listener.remove() }
    }

    fun deleteTeamMember(teamMember: TeamMember)  {
        if (teamMember.imgPath.isNotEmpty()) {
            cloudStorage.reference.child(teamMember.imgPath).delete()
                .addOnSuccessListener { Log.i(tag, "Bild in der Cloud wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "Bild wurde nicht gelöscht! ${e.message}") }
            collection.document(teamMember.id).delete()
                .addOnSuccessListener { Log.i(tag, "TeamMember wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "TeamMember wurde nicht gelöscht! ${e.message}") }
        } else {
            collection.document(teamMember.id).delete()
                .addOnSuccessListener { Log.i(tag, "TeamMember wurde gelöscht") }
                .addOnFailureListener { e -> Log.i(tag, "TeamMember wurde nicht gelöscht! ${e.message}") }
        }
    }

}