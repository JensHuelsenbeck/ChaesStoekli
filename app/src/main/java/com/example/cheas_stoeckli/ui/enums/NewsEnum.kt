package com.example.cheas_stoeckli.ui.enums

enum class NewsEnum(val rawValue: String) {
    NEWS("Neuigkeit"),
    EVENTS("Veranstaltung"),
    REMINDER("Erinnerung");

    companion object {
        fun convertRawValue(rawValue: String): NewsEnum? {
            return entries.find { it.rawValue == rawValue }
        }
    }
}

