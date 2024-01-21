package com.medicalmanager.presentation.appointment.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.model.AppointmentStatus
import com.medicalmanager.domain.use_case.BookAppointmentUseCase
import com.medicalmanager.presentation.appointment.screen.BookingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentViewModel @Inject constructor(
    private val bookAppointmentUseCase: BookAppointmentUseCase
):ViewModel() {
    private val _appointmentBooking: MutableStateFlow<BookingState> = MutableStateFlow(
        BookingState()
    )

    val appointmentBooking: StateFlow<BookingState> = _appointmentBooking




     val selection = CalendarSelection.Date{date ->
         Log.d("SelectedDate","{$date}")
     }

    init {
        bookAppointment(appointment = AppointmentModel( date = selection, status = AppointmentStatus.PENDING))
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