package com.medicalmanager.data.repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.repository.AppointmentRepository
import javax.inject.Inject

class AppointmentRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
):AppointmentRepository {

    private val appointmentsCollection = fireStore.collection("appointments")
    override suspend fun bookAppointment(appointment: AppointmentModel): Boolean {

        try {
            val appointmentData = mapOf(
                "doctorId" to appointment.doctorId,
                "userId" to appointment.userId,
                "date" to appointment.date,
                "status" to appointment.status.name
            )

            //add appointment to fireStore
            appointmentsCollection.add(appointmentData)
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
            return true
        } catch (e: Exception) {

            return false
        }

    }
}