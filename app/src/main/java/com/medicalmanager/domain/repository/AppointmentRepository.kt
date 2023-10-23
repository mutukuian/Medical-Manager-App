package com.medicalmanager.domain.repository

import com.medicalmanager.domain.model.AppointmentModel

interface AppointmentRepository {
    suspend fun bookAppointment(appointment:AppointmentModel):Boolean
}