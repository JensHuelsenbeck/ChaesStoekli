package com.example.cheas_stoeckli.domain.models

import com.google.firebase.Timestamp
import java.util.UUID

data class Raclette (
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val description: String = "",
    val imgDownloadPath: String = "",
    val imgPath: String = "",
    val createdAt: Timestamp = Timestamp.now()
)