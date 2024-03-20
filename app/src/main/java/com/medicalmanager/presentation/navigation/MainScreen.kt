package com.medicalmanager.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.medicalmanager.presentation.navigation.components.BottomNavBar
import com.medicalmanager.presentation.navigation.components.provideBottomNavItems
import com.medicalmanager.presentation.navigation.mainnavigation.MainNavigation

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(
    startDestination:String
){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()


    val rootDestinations = listOf(
        Screen.HomeScreen.route,
        Screen.BookingScreen.route,
        Screen.ProfileScreen.route
    )

    val bottomNavBarState = remember{ mutableStateOf(true) }

    val navBarEntry by navController.currentBackStackEntryAsState()
    bottomNavBarState.value = rootDestinations.contains(navBarEntry?.destination?.route)

    Scaffold(
        bottomBar = {
            AnimatedVisibility(
                visible = bottomNavBarState.value,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it })
            ) {
               BottomNavBar(
                   items = provideBottomNavItems(),
                   navController = navController
               ) {
                   navController.navigate(it.route){
                       popUpTo(navController.graph.startDestinationId)
                       launchSingleTop = true
                       restoreState = true
                   }
               }
            }
        }, scaffoldState = scaffoldState
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        ){
          MainNavigation(
              navController = navController,
              startDestination = startDestination
          )
        }
    }

}