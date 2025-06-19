package com.example.cheas_stoeckli.domain.mappers

import com.example.cheas_stoeckli.domain.models.User
import com.google.firebase.auth.FirebaseUser


object FirebaseAuthUserMapper {

    fun toDomain(dto: FirebaseUser): User {
        return User(
            id = dto.uid,
            email = dto.email ?: "",
            fullName = dto.displayName ?: ""
        )
    }
}