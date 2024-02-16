package com.medicalmanager.domain.model

import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate


data class AppointmentModel(
    val date: CalendarSelection.Date,
    val status:AppointmentStatus
)

enum class AppointmentStatus {
    PENDING,
    CONFIRMED,
    CANCELED
}

