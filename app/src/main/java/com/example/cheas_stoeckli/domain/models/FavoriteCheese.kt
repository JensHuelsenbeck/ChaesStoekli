package com.example.cheas_stoeckli.domain.models

import com.google.firebase.Timestamp
import java.util.UUID

data class FavoriteCheese(
    val id: String = UUID.randomUUID().toString(),
    val createdAt: Timestamp = Timestamp.now()
    )