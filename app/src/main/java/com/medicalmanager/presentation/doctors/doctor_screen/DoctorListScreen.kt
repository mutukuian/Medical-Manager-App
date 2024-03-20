package com.medicalmanager.presentation.doctors.doctor_screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.medicalmanager.domain.model.DoctorModel
import com.medicalmanager.presentation.appointment.screen.AppointmentBookingScreen
import com.medicalmanager.presentation.doctors.viewmodels.DoctorViewModel
import com.medicalmanager.presentation.searchcontroller.search_screen.SearchBarM3
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DoctorListScreen(
    viewModel:DoctorViewModel = hiltViewModel()
){
    val doctorListingState = viewModel.doctorListing.collectAsState(initial = null)




    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Doctors List", style = MaterialTheme.typography.body1)

        Spacer(modifier = Modifier.height(24.dp))

        SearchBarM3()

        Spacer(modifier = Modifier.height(24.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            HandleDoctorListingState(doctorListingState = doctorListingState)


            LaunchedEffect(key1 = doctorListingState.value?.error){
                scope.launch {
                    if (doctorListingState.value?.error?.isNotEmpty() == true){
                        val isError = doctorListingState.value?.error
                        Toast.makeText(context,"$isError", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }



    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HandleDoctorListingState(doctorListingState: State<DoctorListingState?>) {
    when{

        doctorListingState.value?.data != null ->{
            DoctorsList(doctorListingState.value?.data!!)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DoctorsList(docs: List<DoctorModel>) {
    LazyColumn{
        items(docs){doctor ->
            DoctorItem(doctor = doctor)
            Spacer(modifier = Modifier.height(8.dp))

        }
        item{
            val scrollState = rememberLazyListState()
            val isScrollableToBottom = scrollState.layoutInfo
                .visibleItemsInfo
                .lastOrNull()?.index == docs.size-1
            if (isScrollableToBottom){

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DoctorItem(doctor: DoctorModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Left side (Name, Role, Hospital)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                // Load the doctor's image on the right
                AsyncImage(model = doctor.image, contentDescription = null)

                Text(
                    text = doctor.fullName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = doctor.role, fontSize = 16.sp)
                }

                Text(text = doctor.hospitalName, fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
            }

            // Right side (Buttons)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                //book appointment
                var selectedDate by remember{ mutableStateOf<CalendarSelection.Date?>(null) }


                AppointmentBookingScreen(doctor,navigateToBookingScreen = {date->
                    selectedDate = date
                })
            }
        }
    }
}
