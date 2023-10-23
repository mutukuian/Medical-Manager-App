package com.medicalmanager.presentation.appointment.screen

import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.model.AppointmentStatus
import com.medicalmanager.presentation.appointment.view_model.AppointmentViewModel
import java.util.Date

@Composable
fun AppointmentBookingScreen(
    viewModel: AppointmentViewModel = hiltViewModel(),
    doctorId: String,
    userId: String
) {
    var selectedDate by remember { mutableStateOf<Date?>(null) }

    // Create a DatePicker here to select the appointment date and time
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        DatePicker(
            date = selectedDate,
            onDateChange = { newDate ->
                selectedDate = newDate
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        // Create a button to book the appointment
        Button(
            onClick = {
                selectedDate?.let { date ->
                    val appointment = AppointmentModel(doctorId, userId, date, AppointmentStatus.PENDING)
                    viewModel.bookAppointment(appointment)
                }
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

