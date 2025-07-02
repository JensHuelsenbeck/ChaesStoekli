package com.example.cheas_stoeckli.domain.mappers

import com.example.cheas_stoeckli.domain.models.FirestoreOffer
import com.example.cheas_stoeckli.domain.models.Offer
import com.example.cheas_stoeckli.ui.enums.OfferKind

object OfferMapper {

    fun toApp(dto: FirestoreOffer): Offer {
        return Offer(
            id = dto.id,
            title = dto.title,
            text = dto.text,
            bottomText = dto.bottomText,
            imgDownloadPath = dto.imgDownloadPath,
            imgPath = dto.imgPath,
            type = OfferKind.convertRawValue(dto.type) ?: OfferKind.ALLGEMEIN,
            createdAt = dto.createdAt
        )
    }

    fun toFirebase(offer: Offer): FirestoreOffer {
        return FirestoreOffer(
            id = offer.id,
            title = offer.title,
            text = offer.text,
            bottomText = offer.bottomText,
            imgDownloadPath = offer.imgDownloadPath,
            imgPath = offer.imgPath,
            type = offer.type.rawValue,
            createdAt = offer.createdAt
        )
    }
}