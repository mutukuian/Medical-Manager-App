package com.medicalmanager.domain.use_case

import com.medicalmanager.domain.repository.SearchControllerRepository
import javax.inject.Inject

class SearchControllerUseCase @Inject constructor(
    private val searchControllerRepository: SearchControllerRepository
) {
    suspend operator fun invoke(query:String){
        searchControllerRepository.searchDoctor(query)
    }
}