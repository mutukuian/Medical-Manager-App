package com.medicalmanager.presentation.doctors.navigation

sealed class Screen(val route:String) {

    object DoctorListScreen:Screen(route = "doctor_list_screen")

    object DoctorDetailsScreen:Screen(route = "$DoctorDetailsScreen/{doctorImage}/{doctorName}/{doctorHospital}")

}