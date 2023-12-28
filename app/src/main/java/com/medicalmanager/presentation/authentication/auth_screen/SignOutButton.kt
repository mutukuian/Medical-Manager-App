package com.medicalmanager.presentation.authentication.auth_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.medicalmanager.presentation.authentication.auth_view_model.LogOutViewModel
import com.medicalmanager.presentation.authentication.auth_view_model.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun SignOutBtn(
    navController: NavHostController,
    viewModel: LogOutViewModel = hiltViewModel()
){

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val state = viewModel.loginState.collectAsState(initial = null)

    Button(
        onClick = {
     scope.launch {
         viewModel.signOut()
     }
        }) {
        Text(text = "SgnOut")
    }

    Row (
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ){
        if (state.value?.isLoading == true){
            CircularProgressIndicator()
        }
    }

    Spacer(modifier = Modifier.height(24.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LaunchedEffect(key1 = state.value?.data){
            scope.launch {
                if (state.value?.data?.isNotEmpty() == true){
                    val success = state.value?.data
                    Toast.makeText(context,"$success", Toast.LENGTH_SHORT).show()
                }
            }
        }


        LaunchedEffect(key1 = state.value?.error){
            scope.launch {
                if (state.value?.error?.isNotEmpty() == true){
                    val isError = state.value?.error
                    Toast.makeText(context,"$isError", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}