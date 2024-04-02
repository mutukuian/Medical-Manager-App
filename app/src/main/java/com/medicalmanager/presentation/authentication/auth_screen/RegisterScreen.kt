package com.medicalmanager.presentation.authentication.auth_screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.medicalmanager.R
import com.medicalmanager.presentation.authentication.auth_view_model.RegisterViewModel
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavHostController,
    viewModel: RegisterViewModel = hiltViewModel()
){

    Surface {
        Column(modifier = Modifier.fillMaxSize()){
            TopSection()

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                RegisterSection()

                val state = viewModel.registerState.collectAsState(initial = null)
                Row (
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ){
                    if (state.value?.isLoading == true){
                        CircularProgressIndicator()
                    }
                }
                val context = LocalContext.current
                val scope = rememberCoroutineScope()

                Spacer(modifier = Modifier.height(24.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally){

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

                    LoginAccTxt(navController = navController)

                }

            }
        }

    }



}


@Composable
private fun LoginAccTxt(navController: NavController) {
    //val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    Box(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.8f)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        androidx.compose.material3.Text(
            modifier = Modifier.clickable {
                navController.navigate(com.medicalmanager.presentation.navigation.Screen.LogInScreen.route)
            },
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF94A3B8),
                        fontSize = 14.sp,
                    //    fontFamily = Roboto,
                        fontWeight = FontWeight.Normal
                    )
                )
                {
                    append("Already Have An Account?")
                }
                withStyle(
                    style = SpanStyle(
//                        color = uiColor,
                        fontSize = 14.sp,
                       // fontFamily = Roboto,
                        fontWeight = FontWeight.Medium
                    )
                )
                {

                    append(" ")
                    append("Login")
                }
            }
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterSection(
    viewModel:RegisterViewModel= hiltViewModel()
) {

    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val scope = rememberCoroutineScope()
    var emailState by remember  { mutableStateOf("") }

    var passwordState by remember { mutableStateOf("") }


    Spacer(modifier = Modifier.height(15.dp))




    androidx.compose.material3.TextField(
        value = emailState,
        onValueChange = { emailState = it },
        label = {
            androidx.compose.material3.Text(
                text = "Email",
                style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                color = uiColor
            )
        },
        colors = TextFieldDefaults.textFieldColors(

           // containerColor = androidx.compose.material3.MaterialTheme.colorScheme.textFieldContainer,
           // placeholderColor = androidx.compose.material3.MaterialTheme.colorScheme.focusedTextFieldText,

            ),
        trailingIcon = {
            TextButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Text(
                    text = "",
                    style = androidx.compose.material3.MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = uiColor
                )
            }
        },
    )

    Spacer(modifier = Modifier.height(15.dp))

    androidx.compose.material3.TextField(
        value = passwordState,
        onValueChange = { passwordState = it },
        label = {
            androidx.compose.material3.Text(
                text = "Password",
                style = androidx.compose.material3.MaterialTheme.typography.labelMedium,
                color = uiColor
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        colors = TextFieldDefaults.textFieldColors(
            //containerColor = androidx.compose.material3.MaterialTheme.colorScheme.textFieldContainer,
            //placeholderColor = androidx.compose.material3.MaterialTheme.colorScheme.focusedTextFieldText,
        ),
        trailingIcon = {
            TextButton(onClick = { /*TODO*/ }) {
                androidx.compose.material3.Text(
                    text = "",
                    style = androidx.compose.material3.MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = uiColor
                )
            }
        },

        )

    Spacer(modifier = Modifier.height(15.dp))



    Spacer(modifier = Modifier.height(15.dp))
    androidx.compose.material3.Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        onClick = {

            scope.launch {
                viewModel.registerUser(email = emailState, password = passwordState)
            }

        },
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) Color.Blue else Color.Black,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        androidx.compose.material3.Text(
            text = " Register ",
            style = androidx.compose.material3.MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
        )
    }
}

@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.46f),
            painter = painterResource(id = R.drawable.shape),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.app_logo),
                tint = uiColor
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column {
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.the_jobplacer),
                    style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
                    color = uiColor
                )
                androidx.compose.material3.Text(
                    text = stringResource(id = R.string.find_job),
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                    color = uiColor
                )
            }
        }

        androidx.compose.material3.Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            text = stringResource(id = R.string.register),
            style = androidx.compose.material3.MaterialTheme.typography.headlineLarge,
            color = Color.Black
        )
    }
}