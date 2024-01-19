package com.medicalmanager.presentation.profile

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.medicalmanager.presentation.authentication.auth_screen.SignOutBtn

@Composable
fun ProfileScreen(){

    val navController = rememberNavController()

    SignOutBtn(navController = navController)



}