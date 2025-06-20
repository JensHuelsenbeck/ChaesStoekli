package com.example.cheas_stoeckli.domain.mappers

import com.example.cheas_stoeckli.domain.models.FirestoreNews
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind

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
            type = NewsKind.convertRawValue(dto.type) ?: NewsKind.NEWS,
            createdAt = dto.createdAt
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
            type = news.type.rawValue,
            createdAt = news.createdAt

        )
    }

}