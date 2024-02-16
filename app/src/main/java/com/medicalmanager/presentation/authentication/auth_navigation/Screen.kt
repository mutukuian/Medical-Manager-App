package com.medicalmanager.presentation.authentication.auth_navigation

import com.medicalmanager.presentation.bottomnavigation.Screens

sealed class Screen (val route:String){
    object RegisterScreen:Screen(route = "register_screen")
    object LoginScreen:Screen(route = "login_screen")

    object HomeScreen:Screen(route = "home_screen")
    object BookingsScreen:Screen(route = "bookings_screen")
    object ProfileScreen:Screen(route = "profile_screen")


}