package com.medicalmanager.domain.model




data class AppointmentModel(
    val date: String,
    val doctorName:String,
){
    constructor():this("","")
}


