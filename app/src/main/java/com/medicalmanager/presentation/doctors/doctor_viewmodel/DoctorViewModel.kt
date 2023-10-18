package com.medicalmanager.presentation.doctors.doctor_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.model.DoctorModel
import kotlinx.coroutines.flow.collect
import com.medicalmanager.domain.use_case.GetDoctorUseCase
import com.medicalmanager.presentation.doctors.doctor_screen.DoctorListingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DoctorViewModel @Inject constructor(
    private val getDoctorUseCase: GetDoctorUseCase
):ViewModel() {

    private val _doctorListing: MutableStateFlow<DoctorListingState> = MutableStateFlow(
        DoctorListingState()
    )

    val doctorListing: StateFlow<DoctorListingState> = _doctorListing

    init {
        fetchDoctors()
    }

        fun fetchDoctors(){
        viewModelScope.launch {
            try {
                val doctors = getDoctorUseCase()
                _doctorListing.value = DoctorListingState(data = doctors)
            }catch (e:Exception){
                _doctorListing.value = DoctorListingState(error = e.message)
            }
        }
    }

}

/*

private fun fetchDoctors() = viewModelScope.launch {
    getDoctorUseCase().forEach { result ->
        when (result) {
            is Resource.Error -> _doctorListing.value = DoctorListingState(error = result.message)
            is Resource.Loading -> _doctorListing.value = DoctorListingState(isLoading = true)
            is Resource.Success -> {
                val doctorModelList = listOf(DoctorModel(
                    image = result.data?.image ?: "",
                    fullName = result.data?.fullName ?: "",
                    hospitalName = result.data?.hospitalName ?: "",
                    role = result.data?.role ?: ""
                ))
                _doctorListing.value = DoctorListingState(data = doctorModelList)
            }
        }
    }
}
 */