package com.medicalmanager.domain.use_case

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookAppointmentUseCase @Inject constructor(
  private val appointmentRepository: AppointmentRepository
){
    suspend operator fun invoke(appointmentModel: AppointmentModel): Flow<Resource<Task<DocumentReference>>> {
        return appointmentRepository.bookAppointment(appointmentModel)
    }

}