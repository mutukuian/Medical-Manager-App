package com.medicalmanager.presentation.authentication.auth_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.medicalmanager.presentation.authentication.auth_navigation.Screen
import com.medicalmanager.presentation.authentication.auth_view_model.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LogInScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
){
    var email by remember{
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val scope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.),
//            contentDescription = null,
//            modifier = Modifier
//                .size(120.dp)
//                .padding(bottom = 16.dp),
//            contentScale = ContentScale.FillWidth
//        )


        TextField(
            value = email,
            onValueChange = { email = it},
            label = { Text(text = "Email") }
        )

        TextField(
            value = password,
            onValueChange = { password = it},
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(15.dp))


        Button(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
            onClick = {
                scope.launch {
                    viewModel.loginUser(email, password)
                }
            },
            shape = RoundedCornerShape(size = 8.dp)
            ) {
            Text(text = "Login")
        }


        Text(
            text = "Don't have an account ? Register",
            color = MaterialTheme.colors.primary,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable {
                    navController.navigate(Screen.RegisterScreen.route)
                }
                .padding(vertical = 8.dp)
        )
    }

    val state = viewModel.loginState.collectAsState(initial = null)
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
                    Toast.makeText(context,"$success",Toast.LENGTH_SHORT).show()
                }
            }
        }


        LaunchedEffect(key1 = state.value?.error){
            scope.launch {
                if (state.value?.error?.isNotEmpty() == true){
                    val isError = state.value?.error
                    Toast.makeText(context,"$isError",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}