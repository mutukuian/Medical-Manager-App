package com.medicalmanager.presentation.authentication.auth_navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.medicalmanager.presentation.authentication.auth_screen.LogInScreen
import com.medicalmanager.presentation.authentication.auth_screen.RegisterScreen
import com.medicalmanager.presentation.bottomnavigation.AppNavigation


@Composable
fun SetUpNavGraph(
    navController:NavHostController,

){
        NavHost(navController = navController, startDestination = Screen.LoginScreen.route){

            composable(Screen.RegisterScreen.route){
                RegisterScreen(navController = navController)
            }
            composable(Screen.LoginScreen.route){
                LogInScreen(navController = navController)
            }

            composable(Screen.HomeScreen.route){
                    AppNavigation()
                }



        }
}



