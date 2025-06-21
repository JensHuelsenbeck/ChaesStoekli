package com.example.cheas_stoeckli.domain.models

import com.example.cheas_stoeckli.ui.enums.NewsKind
import com.google.firebase.Timestamp
import java.util.UUID

class News(

    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val text: String = "",
    val img: String = "",
    val imgPath: String = "",
    val destination: String = "",
    val date: String = "",
    val time: String = "",
    val type: NewsKind = NewsKind.NEWS,
    val createdAt: Timestamp = Timestamp.now()

    )