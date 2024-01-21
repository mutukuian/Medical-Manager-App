package com.medicalmanager.presentation.doctors.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medicalmanager.presentation.doctors.doctor_details.DoctorDetailScreen
import com.medicalmanager.presentation.doctors.doctor_screen.DoctorListScreen

@Composable
fun SetNavigation(
    navController:NavHostController
){
    NavHost(navController = navController, startDestination = "doctor_list_screen"){
        composable(Screen.DoctorListScreen.route){
            DoctorListScreen()
        }
        composable(Screen.DoctorDetailsScreen.route){backStackEntry->
            val doctorImage = backStackEntry.arguments?.getString("doctorImage")
            val doctorName = backStackEntry.arguments?.getString("doctorName")
            val hospital = backStackEntry.arguments?.getString("hospitalName")
            DoctorDetailScreen(navController,doctorImage = doctorImage, doctorName = doctorName, doctorHospital = hospital)
        }

    }
}