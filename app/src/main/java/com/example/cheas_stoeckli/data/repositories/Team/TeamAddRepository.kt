package com.example.cheas_stoeckli.data.repositories.Team

import android.util.Log
import com.example.cheas_stoeckli.domain.mappers.TeamMemberMapper
import com.example.cheas_stoeckli.domain.models.TeamMember
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TeamAddRepository {

    private val db = Firebase.firestore
    private val collection = db.collection("teamMember")


    fun addTeamMember(
        team: TeamMember,
        onSuccess: () -> Unit,

        ) {
        val firebaseTeamMember = TeamMemberMapper.toFirebase(team)
        collection.document(firebaseTeamMember.id).set(firebaseTeamMember)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("TeamMemberAddRepository", "Fehler beim schreiben: ${e.message}")

            }
    }

}