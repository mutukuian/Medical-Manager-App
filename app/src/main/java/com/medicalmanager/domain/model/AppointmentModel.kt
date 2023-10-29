package com.medicalmanager.domain.model

import com.maxkeppeler.sheets.calendar.models.CalendarSelection


data class AppointmentModel(
    val doctorId:String,
    val userId:String,
    val date: CalendarSelection.Date,
    val status:AppointmentStatus
)

enum class AppointmentStatus {
    PENDING,
    CONFIRMED,
    CANCELED
}

