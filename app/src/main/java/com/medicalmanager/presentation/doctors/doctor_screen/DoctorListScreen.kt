package com.medicalmanager.presentation.doctors.doctor_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.medicalmanager.domain.model.DoctorModel
import com.medicalmanager.presentation.doctors.doctor_viewmodel.DoctorViewModel
import kotlinx.coroutines.launch

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
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            LaunchedEffect(key1 = doctorListingState.value?.data){
//                scope.launch {
//                    if (doctorListingState.value?.data?.isNotEmpty() == true){
//                        val success = doctorListingState.value?.data
//                        Toast.makeText(context,"$success", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
            handleDoctorListingState(doctorListingState = doctorListingState)
            Log.d("success","${handleDoctorListingState(doctorListingState = doctorListingState)}")


            LaunchedEffect(key1 = doctorListingState.value?.error){
                scope.launch {
                    if (doctorListingState.value?.error?.isNotEmpty() == true){
                        val isError = doctorListingState.value?.error
                        Toast.makeText(context,"$isError", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
//        handleDoctorListingState(doctorListingState = doctorListingState)


    }

/*
@Composable
fun DoctorListScreen(
    viewModel: DoctorViewModel = hiltViewModel()
) {
    val doctorListingState = viewModel.doctorListing.collectAsState(initial = DoctorListingState.Loading)

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when (doctorListingState.value) {
            is DoctorListingState.Success -> {
                val doctorList = (doctorListingState.value as DoctorListingState.Success).data
                DoctorsList(doctorList)
            }
            is DoctorListingState.Error -> {
                // Handle error state
                Text(text = "Error: ${(doctorListingState.value as DoctorListingState.Error).error}")
            }
            is DoctorListingState.Loading -> {
                // Handle loading state
                Text(text = "Loading...")
            }
        }
    }
}

 */

}

@Composable
fun handleDoctorListingState(doctorListingState: State<DoctorListingState?>) {
    when{

        doctorListingState.value?.data != null ->{
            DoctorsList(doctorListingState.value?.data!!)
        }
    }
}

@Composable
fun DoctorsList(docs: List<DoctorModel>) {
    LazyColumn{
        items(docs){doctor ->
            DoctorItem(doctor = doctor)
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
fun DoctorItem(doctor: DoctorModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        //Load the doctors image
        //CoilImage(data = doctor.image , contentDescription = "Doctor Image")
//        Image(painter = rememberAsyncImagePainter(), contentDescription = null)


        Text(
            text = doctor.fullName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = doctor.role,
            fontSize = 16.sp
        )

        Text(
            text = doctor.hospitalName,
            fontSize = 16.sp
        )
    }
}
