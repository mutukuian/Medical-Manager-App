package com.medicalmanager.presentation.navigation.mainnavigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.medicalmanager.presentation.authentication.auth_screen.LogInScreen
import com.medicalmanager.presentation.authentication.auth_screen.RegisterScreen
import com.medicalmanager.presentation.bookings.BookingScreen
import com.medicalmanager.presentation.doctors.doctor_screen.DoctorListScreen
import com.medicalmanager.presentation.navigation.Screen
import com.medicalmanager.presentation.profile.ProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(
    navController:NavHostController,
    startDestination:String
){

    var selectedDate by remember{ mutableStateOf<CalendarSelection.Date?>(null) }
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
        composable(route = Screen.BookingScreen.route,
            arguments = listOf(
                navArgument("details"){
                 type = NavType.StringType
                 defaultValue = "booking"
                 nullable = true
                })
            ){entry ->
            BookingScreen(viewModel = hiltViewModel(), navController =navController ,entry.arguments?.getString("details"))
           // BookingsScreen(entry.arguments?.getString("details"))
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }

    }

}