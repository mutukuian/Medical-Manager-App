package com.medicalmanager.presentation.appointment.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.medicalmanager.presentation.appointment.view_model.AppointmentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentBookingScreen(
    viewModel: AppointmentViewModel = hiltViewModel(),
//    doctorId: String,
//    userId: String
) {
//    var selectedDate by remember { mutableStateOf<Date?>(null) }

    // Create a DatePicker here to select the appointment date and time
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date {date ->
            Log.d("SelectedDate","{$date}")
        }
    )


    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        // Create a button to book the appointment
        Button(
            onClick = {
//                selectedDate?.let { date ->
//                    val appointment = AppointmentModel(doctorId, userId, date, AppointmentStatus.PENDING)
//                    viewModel.bookAppointment(appointment)
           //     }
                calendarState.show()

            }
        ) {
            Text("Book Appointment")
        }

        // Observe the state flow from the ViewModel and react accordingly
        val bookingState by viewModel.appointmentBooking.collectAsState()

        // Display loading, data, or error based on bookingState
        when {
            bookingState.isLoading -> {
                // Show loading indicator
                CircularProgressIndicator()
            }
            bookingState.data != null -> {
                // Show confirmation message or navigate to another screen

            }
            bookingState.error != null -> {
                // Show an error message
                Text("Error: ${bookingState.error}")
            }
        }
    }
}

