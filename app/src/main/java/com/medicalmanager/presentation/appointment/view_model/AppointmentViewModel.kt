package com.medicalmanager.presentation.appointment.view_model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.core.common.Resource
import com.medicalmanager.data.repository.NotificationRepository
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.use_case.BookAppointmentUseCase
import com.medicalmanager.domain.use_case.GetDoctorUseCase
import com.medicalmanager.presentation.appointment.screen.BookingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val bookAppointmentUseCase: BookAppointmentUseCase,

):ViewModel() {
    private val _appointmentBooking: MutableStateFlow<BookingState> = MutableStateFlow(
        BookingState()
    )

    val appointmentBooking: StateFlow<BookingState> = _appointmentBooking

    init {
        bookAppointment(appointment = AppointmentModel())
    }



    fun bookAppointment(appointment: AppointmentModel){
        viewModelScope.launch {
            bookAppointmentUseCase.invoke(appointment).collect{result->
                when(result){
                    is Resource.Success ->{_appointmentBooking.value = BookingState(data = appointment)
                    }
                    is Resource.Loading ->{_appointmentBooking.value = BookingState(isLoading = true)
                    }
                    is Resource.Error ->{_appointmentBooking.value = BookingState(error = result.message.toString())}
                }
            }
        }
    }

}