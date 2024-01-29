package com.medicalmanager.presentation.bottomnavigation

enum class Screens {
    DoctorListScreen,
    DoctorDetailsScreen,
    BookingsScreen,
    ProfileScreen
}

sealed class Screenss(val route: String){
    object  LogInScreen:Screenss(route = "login_screen")
}