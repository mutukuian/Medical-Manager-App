package com.medicalmanager.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.medicalmanager.presentation.appointment.screen.AppointmentBookingScreen
import com.medicalmanager.presentation.authentication.auth_navigation.SetUpNavGraph
import com.medicalmanager.presentation.authentication.auth_view_model.UserState
import com.medicalmanager.presentation.doctors.doctor_screen.DoctorListScreen
import com.medicalmanager.presentation.navigation.MainScreen
import com.medicalmanager.presentation.ui.theme.MedicalManagerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainViewModel:MainViewModel

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
                val destination by mainViewModel.startDestination.collectAsState()
                    MainScreen(startDestination = destination)

                }
            }
        }

}
