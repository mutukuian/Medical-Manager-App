package com.medicalmanager.presentation.bookings.booking_viewmodel

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.model.DoctorModel
import com.medicalmanager.domain.use_case.BookAppointmentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class BookingScreenViewModel @Inject constructor(
    private val bookAppointmentUseCase: BookAppointmentUseCase
) : ViewModel() {



    private val _selectedDoctor = MutableStateFlow<DoctorModel?>(null)
    val selectedDoctor: StateFlow<DoctorModel?> = _selectedDoctor

    private val _selectedDate = MutableStateFlow<String?>(null)
    val selectedDate: StateFlow<String?> = _selectedDate.asStateFlow()

    init {

        bookAppointment()
//        val arguments = navController.currentBackStackEntry?.arguments
//        val doctor = arguments?.getParcelable<DoctorModel?>("selectedDoctor")
//        _selectedDoctor.value = doctor
    }

    fun onDateSelected(date: String) {
        _selectedDate.value = date
    }

    fun bookAppointment() {
        val doctor = _selectedDoctor.value ?: return
        val date = _selectedDate.value ?: return
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                // Use BookAppointmentUseCase to book the appointment
                bookAppointmentUseCase.invoke(appointmentModel = AppointmentModel("",""))
            }
            if (result != null ) {
                // Show booking success message or perform other actions
                Log.d("Main","${date},${doctor}")
            } else {
                // Handle booking failure (show error message, etc.)
            }
        }
    }
}


