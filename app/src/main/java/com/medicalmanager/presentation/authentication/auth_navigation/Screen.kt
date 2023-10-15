package com.medicalmanager.presentation.authentication.auth_navigation

sealed class Screen (val route:String){
    object RegisterScreen:Screen(route = "register_screen")
    object LoginScreen:Screen(route = "login_screen")
}