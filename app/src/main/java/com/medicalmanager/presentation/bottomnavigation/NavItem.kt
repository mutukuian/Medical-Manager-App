package com.medicalmanager.presentation.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.medicalmanager.presentation.authentication.auth_navigation.Screen

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems= listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.DoctorListScreen.name
    ),
    NavItem(
        label = "Bookings",
        icon = Icons.Default.Check,
        route = Screens.BookingsScreen.name
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Default.Person,
        route = Screens.ProfileScreen.name
    ),
)
