package com.example.cheas_stoeckli.domain.models

import com.example.cheas_stoeckli.ui.enums.MilkType
import com.google.firebase.Timestamp
import java.util.UUID

data class Cheese (
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val description: String = "",
    val milkType: MilkType = MilkType.COW,
    val createdAt: Timestamp = Timestamp.now()
)