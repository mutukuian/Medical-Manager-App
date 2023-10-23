package com.medicalmanager.domain.model

import java.util.Date

data class AppointmentModel(
    val doctorId:String,
    val userId:String,
    val date:Date,
    val status:AppointmentStatus
)

enum class AppointmentStatus {
    PENDING,
    CONFIRMED,
    CANCELED
}
