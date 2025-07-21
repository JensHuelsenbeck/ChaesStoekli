package com.example.cheas_stoeckli.utils

import java.time.LocalTime

enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
data class TimeRange(val start: LocalTime, val end: LocalTime)
val openingHours: Map<WeekDay, List<TimeRange>> = mapOf(
    WeekDay.MONDAY to listOf(
        TimeRange(LocalTime.of(8, 0), LocalTime.of(12, 0)),
        TimeRange(LocalTime.of(14, 0), LocalTime.of(18, 30))),
    WeekDay.TUESDAY to listOf(
        TimeRange(LocalTime.of(8, 0), LocalTime.of(12, 0)),
        TimeRange(LocalTime.of(14, 0), LocalTime.of(18, 30))),
    WeekDay.WEDNESDAY to listOf(
        TimeRange(LocalTime.of(8, 0), LocalTime.of(12, 0)),
        TimeRange(LocalTime.of(14, 0), LocalTime.of(18, 30))),
    WeekDay.THURSDAY to listOf(TimeRange(LocalTime.of(8, 0), LocalTime.of(12, 0))),
    WeekDay.FRIDAY to listOf(
        TimeRange(LocalTime.of(8, 0), LocalTime.of(12, 0)),
        TimeRange(LocalTime.of(14, 0), LocalTime.of(18, 30))),
    WeekDay.SATURDAY to listOf(TimeRange(LocalTime.of(8, 0), LocalTime.of(14, 0))),
    WeekDay.SUNDAY to emptyList()
)