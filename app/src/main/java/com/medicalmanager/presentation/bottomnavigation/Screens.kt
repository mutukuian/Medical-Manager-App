package com.medicalmanager.presentation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.medicalmanager.presentation.authentication.auth_navigation.Screen
import com.medicalmanager.presentation.bookings.BookingsScreen
import com.medicalmanager.presentation.doctors.doctor_screen.DoctorListScreen
import com.medicalmanager.presentation.profile.ProfileScreen

enum class Screens {
    DoctorListScreen,
    DoctorDetailsScreen,
    BookingsScreen,
    ProfileScreen
}



