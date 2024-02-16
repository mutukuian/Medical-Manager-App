package com.medicalmanager.data.repository


import android.util.Log

import com.google.firebase.firestore.FirebaseFirestore

import com.medicalmanager.domain.model.DoctorModel
import com.medicalmanager.domain.repository.DoctorRepository

import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class DoctorRepositoryImpl @Inject constructor(
    fireStore: FirebaseFirestore
) :DoctorRepository{


    private val doctorsCollection = fireStore.collection("Doctors")
    override suspend fun fetchAllDoctors(): List<DoctorModel> {
        val doctorList = mutableListOf<DoctorModel>()
        doctorsCollection.limit(2).get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot.documents){
                val doctor = DoctorModel(
                    fullName = document.get("Name").toString(),
                    role = document.get("Role").toString(),
                    hospitalName = document.get("Hospital").toString(),
                    image = document.get("ImageUrl").toString()
                )

                doctorList.add(doctor)
                Log.d("TAG","{$doctorList}")
            }
        }.await()
        return doctorList
    }

}


