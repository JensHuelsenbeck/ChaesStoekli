package com.example.cheas_stoeckli.ui.enums

enum class NewsKind(val rawValue: String) {
    NEWS("Neuigkeit"),
    EVENTS("Veranstaltung"),
    REMINDER("Erinnerung");

    companion object {
        fun convertRawValue(rawValue: String): NewsKind? {
            return entries.find { it.rawValue == rawValue }
        }
    }
}

