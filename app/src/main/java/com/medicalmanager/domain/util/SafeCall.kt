package com.medicalmanager.domain.util

import com.medicalmanager.core.common.Resource

import kotlinx.coroutines.flow.flow
import timber.log.Timber

import java.io.IOError
import java.io.IOException


suspend fun <T: Any> safeAppCall(
    block: suspend () -> T
) = flow {
    emit(Resource.Loading())
    try {
        val result = block()
        emit(Resource.Success(result))
    } catch (error:java.lang.Exception) {
        emit(when(error) {
            401 -> Resource.Error("Your not authorized to access this functionality")
            429 -> Resource.Error("Due to too many request, please wait for a few minutes before making the next request")
            501 -> Resource.Error("Server Error, Please try again later")
            413 -> Resource.Error("The data uploaded is too large")
            else -> Resource.Error("Unknown error has occurred " + error.message)
        })
        Timber.e(error)
    } catch (error: IOError) {
        emit(Resource.Error("Network Error. Please check on your network"))
    } catch (error: Exception) {
        emit(Resource.Error("Unknown error has occurred"))
        Timber.e(error)
    }

}