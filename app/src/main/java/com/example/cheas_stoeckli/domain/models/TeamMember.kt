package com.example.cheas_stoeckli.domain.models

import com.example.cheas_stoeckli.ui.enums.MemberPosition
import com.google.firebase.Timestamp
import java.util.UUID

data class TeamMember (
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val description: String = "",
    val imgDownloadPath: String = "",
    val imgPath: String = "",
    val position: MemberPosition = MemberPosition.NONE,
    val createdAt: Timestamp = Timestamp.now()
)