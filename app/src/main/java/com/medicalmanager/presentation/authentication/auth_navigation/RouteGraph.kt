package com.medicalmanager.presentation.authentication.auth_navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medicalmanager.presentation.authentication.auth_screen.LogInScreen
import com.medicalmanager.presentation.authentication.auth_screen.RegisterScreen

@Composable
fun SetUpNavGraph(
    navController:NavHostController
){
    NavHost(navController = navController, startDestination = "register_screen"){
        composable(Screen.RegisterScreen.route){
            RegisterScreen(navController = navController, viewModel = viewModel())
        }

        composable(Screen.LoginScreen.route){
            LogInScreen(navController = navController, viewModel = viewModel())
        }
    }
}