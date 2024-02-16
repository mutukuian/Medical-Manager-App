package com.medicalmanager.presentation.navigation.mainnavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medicalmanager.presentation.authentication.auth_screen.LogInScreen
import com.medicalmanager.presentation.authentication.auth_screen.RegisterScreen
import com.medicalmanager.presentation.bookings.BookingsScreen
import com.medicalmanager.presentation.doctors.doctor_screen.DoctorListScreen
import com.medicalmanager.presentation.navigation.Screen
import com.medicalmanager.presentation.profile.ProfileScreen

@Composable
fun MainNavigation(
    navController:NavHostController,
    startDestination:String
){
    NavHost(navController = navController, startDestination = startDestination){
        composable(route = Screen.LogInScreen.route){
            LogInScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route){
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.HomeScreen.route){
            DoctorListScreen()
        }
        composable(route = Screen.BookingScreen.route){
            BookingsScreen()
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }

    }

}