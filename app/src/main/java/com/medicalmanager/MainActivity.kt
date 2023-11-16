package com.medicalmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.medicalmanager.presentation.authentication.auth_navigation.SetUpNavGraph
import com.medicalmanager.presentation.ui.theme.MedicalManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicalManagerTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                    val navController = rememberNavController()
                    SetUpNavGraph(navController = navController)

//               AppointmentBookingScreen(doctorId = "", userId = "")

//                SearchBarM3()

//                AppNavigation()

                }
            }
        }

}
