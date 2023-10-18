package com.medicalmanager.domain.model

data class DoctorModel(
    val fullName:String,
    val role:String,
    val hospitalName:String,
    val image:String
){
    constructor():this("","","","")
}


