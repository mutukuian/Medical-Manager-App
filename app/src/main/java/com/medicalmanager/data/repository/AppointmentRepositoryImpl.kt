package com.medicalmanager.data.repository


import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.repository.AppointmentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
):AppointmentRepository {

    private val appointmentsCollection = fireStore.collection("appointments")
    override suspend fun bookAppointment(appointment: AppointmentModel): Flow<Resource<Task<DocumentReference>>> {
        return flow {
            emit(Resource.Loading())

                val appointmentData = mapOf(
//                "doctorId" to appointment.doctorId,
//                "userId" to appointment.userId,
                    "date" to appointment.date,
                    "status" to appointment.status.name
                )

                //add appointment to fireStore
               val result = appointmentsCollection.add(appointmentData)
                    emit(Resource.Success(result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }

    }

}