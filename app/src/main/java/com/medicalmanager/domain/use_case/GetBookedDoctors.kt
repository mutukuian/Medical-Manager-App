package com.medicalmanager.domain.use_case

import android.util.Log
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.repository.AppointmentRepository
import javax.inject.Inject

class GetBookedDoctors @Inject constructor(
    private val repo:AppointmentRepository
) {

//    suspend operator fun invoke(
//        doctorName: List<String>,
//        bookedDoctors: (doctors: List<AppointmentModel>) -> Unit
//    ) = repo.getAllBookedDoctors(
//        doctorName = doctorName,
//        bookedDoctors = {
//            bookedDoctors(it)
//
//            Log.d("bookedUseCase", "all booked houses = ${it.size}")
//        }
//    )

}