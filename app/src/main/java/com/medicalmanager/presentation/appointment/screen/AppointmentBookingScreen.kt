package com.medicalmanager.presentation.appointment.screen


import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
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
import com.medicalmanager.domain.model.DoctorModel
import com.medicalmanager.presentation.appointment.view_model.AppointmentViewModel
import com.medicalmanager.presentation.appointment.view_model.NotificationViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentBookingScreen(
    doctor:DoctorModel,
    viewModel: AppointmentViewModel = hiltViewModel(),
    view: NotificationViewModel = hiltViewModel(),
    navigateToBookingScreen: (CalendarSelection.Date) -> Unit
) {
    var selectedDate by remember { mutableStateOf<CalendarSelection.Date?>(null) }
    var bookedDates by remember { mutableStateOf<Set<String>?>(null) }

    LaunchedEffect(key1 = viewModel.appointmentBooking.value) {
        if (viewModel.appointmentBooking.value.data != null){
            val bookedAppointment = viewModel.appointmentBooking.value.data!!
            val formattedBookedDate = bookedAppointment.date.toString()
            bookedDates?.let {
                bookedDates = it.plus(formattedBookedDate)
            }?:run {
                bookedDates = setOf(formattedBookedDate)
            }
        }
    }

    val isDateAvailable = bookedDates?.contains(selectedDate?.toString())==false

    val context = LocalContext.current


    // Create a DatePicker here to select the appointment date and time
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date{date->

           selectedDate = selectedDate
            if (isDateAvailable) {
                val formattedDate = date.toString()
                val notificationContent = "Hello, Your Appointment is Scheduled on $formattedDate.If you'd like to reschedule,please Call +254707014276.Thank you."
                view.triggerNotification("Appointment Scheduled", notificationContent)
            }else{
                Toast.makeText(context,"Date unavailable.Please choose another date.",Toast.LENGTH_SHORT).show()
            }

//

            Log.d("SelectedDate" ,"$date")
            //Toast.makeText(context,"You have successfully booked appointment on $date",Toast.LENGTH_SHORT).show()
            selectedDate?.let { selected->
                val appointment = AppointmentModel(
                    date = selected.toString(),
                    doctorName=doctor.fullName
                )
                viewModel.bookAppointment(appointment)
            }

        }

    )



    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Create a button to book the appointment
        Button(
            onClick = {
                calendarState.show()
            }
        ) {
            Text("Book Appointment")
        }



        selectedDate?.let { date ->
//            Button(
//                onClick = {
//                    navigateToBookingScreen(date)
//                }
//            ){
            Text("Confirm Appointment for $date")


        }

        // Observe the state flow from the ViewModel and react accordingly
        HandleState()
    }


}




@RequiresApi(Build.VERSION_CODES.O)
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