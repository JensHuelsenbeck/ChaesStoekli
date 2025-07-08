package com.example.cheas_stoeckli.ui.enums

enum class MemberPosition(val rawValue: String) {
    NONE("Kein Teamitglied"),
    CHEF("Chef"),
    AN("Arbeitnehmer");

    companion object {
        fun convertRawValue(rawValue: String): MemberPosition? {
            return MemberPosition.entries.find { it.rawValue == rawValue }
        }
    }
}
