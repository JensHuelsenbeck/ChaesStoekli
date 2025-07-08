package com.example.cheas_stoeckli.ui.enums

enum class OfferKind(val rawValue: String) {
    ALLGEMEIN("Allgemein"),
    KAESE("Käse"),
    RACLETTE("Raclette"),
    FONDUE("Fondue");

    companion object {
        fun convertRawValue(rawValue: String): OfferKind? {
            return OfferKind.entries.find { it.rawValue == rawValue }
        }
    }

}