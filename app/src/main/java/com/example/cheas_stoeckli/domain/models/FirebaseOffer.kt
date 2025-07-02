package com.example.cheas_stoeckli.domain.models

import com.example.cheas_stoeckli.ui.enums.OfferKind
import com.google.firebase.Timestamp
import java.util.UUID

class FirestoreOffer(

    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val text: String = "",
    val bottomText: String = "",
    val imgDownloadPath: String = "",
    val imgPath: String = "",
    val type: String = OfferKind.ALLGEMEIN.rawValue,
    val createdAt: Timestamp = Timestamp.now()

)