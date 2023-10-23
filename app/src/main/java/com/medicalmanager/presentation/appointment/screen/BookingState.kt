package com.medicalmanager.presentation.appointment.screen

import com.medicalmanager.domain.model.AppointmentModel

data class BookingState(
val isLoading:Boolean = false,
val data:AppointmentModel?= null,
val error:String?= null
)

