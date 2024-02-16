package com.medicalmanager.presentation.authentication.auth_screen

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.medicalmanager.R
import com.medicalmanager.core.common.Constants.CLIENT_ID
import com.medicalmanager.presentation.authentication.auth_view_model.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LogInScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
){


    val googleSignInState = viewModel.googleState.value


    val launcher =  rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()){
        val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val result = account.getResult(ApiException::class.java)
            val credentials = GoogleAuthProvider.getCredential(result.idToken,null)
            viewModel.googleSignIn(credentials)
        }catch (it:ApiException){
            print(it)
        }
    }



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
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center

        ){
            IconButton(onClick = {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(CLIENT_ID)
                    .build()

                val googleSignInClient = GoogleSignIn.getClient(context,gso)

                launcher.launch(googleSignInClient.signInIntent)


            }) {
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.google_icon),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.Unspecified
                )
            }
        }


        Text(
            text = "Don't have an account ? Register",
            color = MaterialTheme.colors.primary,
            fontSize = 14.sp,
            modifier = Modifier
                .clickable {
                    navController.navigate(com.medicalmanager.presentation.navigation.Screen.RegisterScreen.route)
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
        LaunchedEffect(key1 = state.value?.data) {
            scope.launch {
                if (state.value?.data?.isNotEmpty() == true) {
                    val success = state.value?.data
                    navController.navigate(com.medicalmanager.presentation.navigation.Screen.HomeScreen.route)
                    Toast.makeText(context, "$success", Toast.LENGTH_SHORT).show()
                }
            }
        }


        LaunchedEffect(key1 = state.value?.error) {
            scope.launch {
                if (state.value?.error?.isNotEmpty() == true) {
                    val isError = state.value?.error
                    Toast.makeText(context, "$isError", Toast.LENGTH_SHORT).show()
                }
            }
        }
        LaunchedEffect(key1 = googleSignInState.success) {
            scope.launch {
                if (googleSignInState.success != null ) {
                    navController.navigate(com.medicalmanager.presentation.navigation.Screen.HomeScreen.route)
                    Toast.makeText(context, "SignIn Success", Toast.LENGTH_SHORT).show()
                }
            }
        }



        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            if (googleSignInState.loading) {
                CircularProgressIndicator()
            }
        }
    }
}