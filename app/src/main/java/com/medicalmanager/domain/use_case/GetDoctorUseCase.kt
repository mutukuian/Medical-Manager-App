package com.medicalmanager.domain.use_case

import com.medicalmanager.domain.model.DoctorModel
import com.medicalmanager.domain.repository.DoctorRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetDoctorUseCase @Inject constructor(
    private val doctorRepository: DoctorRepository

) {
    suspend operator fun invoke(): List<DoctorModel>{
       return doctorRepository.fetchAllDoctors()
    }

}