package com.medicalmanager.presentation.navigation

sealed class Screen(val route:String) {
    object LogInScreen:Screen("login")
    object RegisterScreen:Screen("register")
    object HomeScreen:Screen("home")
    object BookingScreen:Screen("booking")
    object ProfileScreen:Screen("profile")

}