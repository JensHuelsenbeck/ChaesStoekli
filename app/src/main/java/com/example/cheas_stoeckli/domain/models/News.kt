package com.example.cheas_stoeckli.domain.models

import com.example.cheas_stoeckli.ui.enums.NewsEnum
import java.util.UUID

class News(

    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val text: String = "",
    val img: String = "",
    val destination: String = "",
    val date: String = "",
    val time: String = "",
    val type: NewsEnum = NewsEnum.NEWS

    )