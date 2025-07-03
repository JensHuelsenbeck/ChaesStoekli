package com.example.cheas_stoeckli.domain.mappers

import com.example.cheas_stoeckli.domain.models.FiresbaseNews
import com.example.cheas_stoeckli.domain.models.News
import com.example.cheas_stoeckli.ui.enums.NewsKind

object NewsMapper {

    fun toApp(dto: FiresbaseNews): News {
        return News(
            id = dto.id,
            title = dto.title,
            text = dto.text,
            imgDownloadPath = dto.imgDownloadPath,
            imgPath = dto.imgPath,
            destination = dto.destination,
            date = dto.date,
            time = dto.time,
            type = NewsKind.convertRawValue(dto.type) ?: NewsKind.NEWS,
            createdAt = dto.createdAt
        )
    }

    fun toFirebase(news: News): FiresbaseNews {
        return FiresbaseNews(
            id = news.id,
            title = news.title,
            text = news.text,
            imgDownloadPath = news.imgDownloadPath,
            imgPath = news.imgPath,
            destination = news.destination,
            date = news.date,
            time = news.time,
            type = news.type.rawValue,
            createdAt = news.createdAt

        )
    }
}