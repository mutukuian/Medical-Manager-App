package com.medicalmanager.presentation.bottomnavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medicalmanager.presentation.authentication.auth_navigation.Screen
import com.medicalmanager.presentation.authentication.auth_screen.LogInScreen
import com.medicalmanager.presentation.bookings.BookingsScreen
import com.medicalmanager.presentation.doctors.doctor_details.DoctorDetailScreen
import com.medicalmanager.presentation.doctors.doctor_screen.DoctorListScreen
import com.medicalmanager.presentation.profile.ProfileScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination


                listOfNavItems.forEach {navItem ->
                NavigationBarItem(
                    selected =currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                    onClick = {
                              navController.navigate(navItem.route){
                                  popUpTo(navController.graph.findStartDestination().id){
                                      saveState = true
                                  }
                                  launchSingleTop = true
                                  restoreState = true
                              }
                    },
                    icon = {
                           Icon(
                               imageVector =navItem.icon,
                               contentDescription =null
                           )
                    },
                    label = {
                        Text(text = navItem.label)
                    }
                )

                }
            }

        }
    ) {paddingValues ->
    NavHost(
        navController = navController,
        startDestination = Screens.DoctorListScreen.name,
        modifier =Modifier.padding(paddingValues)
       ){
            composable(route = Screens.DoctorListScreen.name){
                DoctorListScreen()
            }
        composable(route = Screens.BookingsScreen.name){
                BookingsScreen()
            }
        composable(route = Screens.ProfileScreen.name){
                ProfileScreen(navController)
            }
        composable(route = Screenss.LogInScreen.route){
            LogInScreen(navController = navController)
        }
        
        composable(route = Screens.DoctorDetailsScreen.name){backStackEntry->
            val doctorImage = backStackEntry.arguments?.getString("doctorImage")
            val doctorName = backStackEntry.arguments?.getString("doctorName")
            val hospital = backStackEntry.arguments?.getString("hospitalName")
            DoctorDetailScreen(
                navController = navController ,
                doctorImage = doctorImage,
                doctorName =doctorName,
                doctorHospital =hospital
            )
        }
        }


    }

}