package com.example.cheas_stoeckli.domain.models

import com.example.cheas_stoeckli.ui.enums.NewsKind
import com.google.firebase.Timestamp
import java.util.UUID

class FiresbaseNews(

    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val text: String = "",
    val imgDownloadPath: String = "",
    val imgPath: String = "",
    val destination: String = "",
    val date: String = "",
    val time: String = "",
    val type: String = NewsKind.NEWS.rawValue,
    val createdAt: Timestamp = Timestamp.now()

)