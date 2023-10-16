package com.medicalmanager.presentation.doctors.doctor_screen

import com.medicalmanager.domain.model.DoctorModel

data class DoctorListingState(
    val isLoading:Boolean = false,
    val data:List<DoctorModel>?= null,
    val error:String?= null
)
