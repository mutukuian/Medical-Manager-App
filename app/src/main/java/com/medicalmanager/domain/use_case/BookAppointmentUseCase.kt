package com.medicalmanager.domain.use_case

import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.repository.AppointmentRepository
import javax.inject.Inject

class BookAppointmentUseCase @Inject constructor(
  private val appointmentRepository: AppointmentRepository
){

    suspend operator fun invoke(appointmentModel: AppointmentModel):Boolean{
        return appointmentRepository.bookAppointment(appointmentModel)
    }

}