package com.medicalmanager.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.model.AppointmentModel
import kotlinx.coroutines.flow.Flow

interface AppointmentRepository {
    suspend fun bookAppointment(appointment:AppointmentModel): Flow<Resource<Task<DocumentReference>>>
}