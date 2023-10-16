package com.medicalmanager.domain.repository

import com.medicalmanager.domain.model.DoctorModel

interface DoctorRepository {

    suspend fun fetchAllDoctors():List<DoctorModel>
}