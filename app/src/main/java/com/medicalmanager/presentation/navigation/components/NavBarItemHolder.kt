package com.medicalmanager.presentation.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.medicalmanager.presentation.navigation.Screen

data class NavBarItemHolder(
    val title:String,
    val icon:ImageVector,
    val route:String
)

fun provideBottomNavItems() = listOf(
    NavBarItemHolder(
    title= "",
    icon = Icons.Default.Home,
    route = Screen.HomeScreen.route
),
    NavBarItemHolder(
    title= "",
    icon = Icons.Default.Check,
    route = Screen.BookingScreen.route
),
    NavBarItemHolder(
    title= "",
    icon = Icons.Default.Person,
    route = Screen.ProfileScreen.route
)

)
