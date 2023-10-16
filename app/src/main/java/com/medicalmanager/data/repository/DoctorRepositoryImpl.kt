package com.medicalmanager.data.repository


import com.google.firebase.firestore.FirebaseFirestore
import com.medicalmanager.domain.model.DoctorModel
import com.medicalmanager.domain.repository.DoctorRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DoctorRepositoryImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) :DoctorRepository{

    private val doctorsCollection = fireStore.collection("Doctors")
    override suspend fun fetchAllDoctors(): List<DoctorModel> {

        val doctorList = mutableListOf<DoctorModel>()
        doctorsCollection.get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents){
                val doctor = document.toObject(DoctorModel::class.java)
                if (doctor != null){
                    doctorList.add(doctor)
                }
            }
        }.await()
        return doctorList
    }
}