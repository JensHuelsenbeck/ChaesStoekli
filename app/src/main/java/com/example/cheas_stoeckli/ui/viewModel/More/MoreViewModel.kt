package com.example.cheas_stoeckli.ui.viewModel.More

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheas_stoeckli.utils.WeekDay
import com.example.cheas_stoeckli.utils.openingHours
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import java.time.LocalTime

class MoreViewModel(): ViewModel() {

    val isOpenNow: StateFlow<Boolean> = flow {
        emit(isCurrentlyOpen())
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), false)

    private fun isCurrentlyOpen(): Boolean {
        val now = LocalTime.now()
        val today = LocalDate.now().dayOfWeek
        val currentDay = WeekDay.valueOf(today.name)

        val hoursToday = openingHours[currentDay] ?: return false
        return hoursToday.any { now.isAfter(it.start) && now.isBefore(it.end) }
    }

    val daysWithHours = listOf(
        "Montag" to "8:00–12:00  14:00–18:30",
        "Dienstag" to "8:00–12:00  14:00–18:30",
        "Mittwoch" to "8:00–12:00  14:00–18:30",
        "Donnerstag" to "8:00–12:00",
        "Freitag" to "8:00–12:00  14:00–18:30",
        "Samstag" to "8:00–14:00",
        "Sonntag" to "geschlossen"
    )

}