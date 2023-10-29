package com.medicalmanager.domain.repository

import com.medicalmanager.domain.model.SearchControllerModel

interface SearchControllerRepository {
    suspend fun searchDoctor(query:String):List<SearchControllerModel>
}