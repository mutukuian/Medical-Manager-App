package com.medicalmanager.presentation.bookings


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.medicalmanager.presentation.bookings.booking_viewmodel.BookingScreenViewModel



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookingScreen(
    viewModel: BookingScreenViewModel,
    navController: NavController,
    string: String?
) {
    val doctor = viewModel.selectedDoctor.collectAsState()
    val date = viewModel.selectedDate.collectAsState()

    Column {
        if (doctor.value != null) {
            Text("Appointment for Dr. ${doctor.value!!.fullName}")
            Spacer(Modifier.height(8.dp))
//            DatePicker(
//                initialSelection = date.value ?: LocalDate.now().toString(), // Set initial date
//                onDateSelected = { viewModel.onDateSelected(it.toString()) }
//            )
            Button(onClick = { viewModel.bookAppointment() }) {
                Text("Book Appointment")
            }
        } else {
            // Handle case where doctor is not available (error message, etc.)
        }
    }
}




























//fun BookingsScreen(selectedDate: CalendarSelection.Date) {
//    // Extract day and year from selected date
////    val day = selectedDate.selectedDate?.dayOfMonth
////    val year = selectedDate.selectedDate?.year
////
////    Column(
////        modifier = Modifier.fillMaxSize(),
////        verticalArrangement = Arrangement.Center,
////        horizontalAlignment = Alignment.CenterHorizontally
////    ){
////        Card(
////            modifier = Modifier.padding(16.dp),
////            backgroundColor = Color.White,
////            elevation = 4.dp
////        ) {
////            Column(
////                modifier = Modifier.padding(16.dp)
////            ) {
////                Text("Booking Details", fontWeight = FontWeight.Bold)
////                Spacer(modifier = Modifier.height(8.dp))
////                Text("Selected Day: $day")
////                Spacer(modifier = Modifier.height(8.dp))
////                Text("Selected Year: $year")
////            }
////        }
////    }
//}

