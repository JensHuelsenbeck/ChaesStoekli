package com.example.cheas_stoeckli.domain.mappers

import com.example.cheas_stoeckli.domain.models.FirestoreNews
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsEnum

object NewsMapper {

    fun toDomain(dto: FirestoreNews): News {
        return News(
            id = dto.id,
            title = dto.title,
            text = dto.text,
            img = dto.img,
            destination = dto.destination,
            date = dto.date,
            time = dto.time,
            type = NewsEnum.convertRawValue(dto.type) ?: NewsEnum.NEWS
        )
    }

    fun fromDomain(news: News): FirestoreNews {
        return FirestoreNews(
            id = news.id,
            title = news.title,
            text = news.text,
            img = news.img,
            destination = news.destination,
            date = news.date,
            time = news.time,
            type = news.type.rawValue
        )
    }

}