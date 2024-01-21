package com.medicalmanager.presentation.appointment.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.medicalmanager.domain.model.AppointmentModel
import com.medicalmanager.domain.model.AppointmentStatus
import com.medicalmanager.presentation.appointment.view_model.AppointmentViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentBookingScreen(
    viewModel: AppointmentViewModel = hiltViewModel(),
//    doctorId: String,
//    userId: String
) {
    var selectedDate by remember { mutableStateOf<CalendarSelection.Date?>(null) }

    // Create a DatePicker here to select the appointment date and time
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date {
            selectedDate = selectedDate
            Log.d("date" ,"$selectedDate")

        }

    )

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        // Create a button to book the appointment
        Button(

            onClick = {
                calendarState.show()
                selectedDate?.let { date ->
                    val appointment = AppointmentModel( date, AppointmentStatus.PENDING)
                    viewModel.bookAppointment(appointment)
                }

            }
        ) {
            Text("Book Appointment")
        }

        // Observe the state flow from the ViewModel and react accordingly
        HandleState()
    }


}


@Composable
fun HandleState(viewModel: AppointmentViewModel = hiltViewModel()){

    val state by viewModel.appointmentBooking.collectAsState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current



    LaunchedEffect(key1 = state.error){
        scope.launch {
            if (state.error !=null){
                Toast.makeText(context,"Error: ${state.error}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}