package com.medicalmanager.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DoctorModel(
    val fullName:String,
    val role:String,
    val hospitalName:String,
    val image:String
):Parcelable


