package com.medicalmanager.domain.use_case


import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.model.SearchControllerModel
import com.medicalmanager.domain.repository.SearchControllerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

import javax.inject.Inject

class SearchControllerUseCase @Inject constructor(
    private val searchControllerRepository: SearchControllerRepository
) {
    operator fun invoke(query:String):Flow<Resource<List<SearchControllerModel>>> = flow{
        try{
            emit(Resource.Loading())

            val search = searchControllerRepository.searchDoctor(query)
            emit(Resource.Success(search))
        }
        catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?: "An unexpected error occurred"))
        }
        catch (e:IOException){
            emit(Resource.Error("Couldn't reach server.Check your connection"))
        }
    }

}