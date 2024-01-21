package com.medicalmanager.presentation.doctors.doctor_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
//import coil.compose.rememberImagePainter

//,.
@Composable
fun DoctorDetailScreen(navController: NavController,doctorImage:String?,doctorName: String?,doctorHospital: String?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //display doctors image
        Image(
            painter = rememberAsyncImagePainter(model = doctorImage ?: ""),
            contentDescription = "Doctor Image",
            modifier = Modifier
                .size(120.dp)
                .clip(shape = CircleShape)
                .background(MaterialTheme.colors.primary)
        )



        Spacer(modifier = Modifier.height(16.dp))

        //display doctors name
        Text(
            text = doctorName ?: "Doctor Name",
            style = MaterialTheme.typography.h5
        )

        Text(
            text = doctorHospital ?: "Doctor Hospital",
            style = MaterialTheme.typography.h5
        )

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Go Back")
        }

    }
}