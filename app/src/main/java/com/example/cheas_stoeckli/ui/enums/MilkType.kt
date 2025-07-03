package com.example.cheas_stoeckli.ui.enums

enum class MilkType(val rawValue: String) {
    ALL("Alle Milcharten"),
    COW("Kuhmilch"),
    GOAT("Ziegenmilch"),
    SHEEP("Schafmilch");

    companion object {
        fun convertRawValue(rawValue: String): MilkType? {
            return MilkType.entries.find { it.rawValue == rawValue }
        }
    }

}