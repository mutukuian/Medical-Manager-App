package com.medicalmanager.presentation.authentication.auth_screen

data class AuthState(
    val isLoading: Boolean = false,
    val data:String?= "",
    val error:String?= null
)