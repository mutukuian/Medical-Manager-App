package com.medicalmanager.presentation.authentication.auth_view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.repository.AuthRepository
import com.medicalmanager.presentation.authentication.auth_screen.AuthState
import com.medicalmanager.presentation.authentication.google_auth.GoogleSignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) :ViewModel() {

    private val _loginState = Channel<AuthState>()
    val loginState = _loginState.receiveAsFlow()


    val _googleState = mutableStateOf(GoogleSignInState())
    val googleState: State<GoogleSignInState> = _googleState


    fun googleSignIn(credential:AuthCredential) = viewModelScope.launch {
        repository.googleSignIng(credential).collect{result ->
            when(result){
                is Resource.Success ->{_googleState.value= GoogleSignInState(success = result.data)}
                is Resource.Loading ->{_googleState.value = GoogleSignInState(loading = true)}
                is Resource.Error ->{_googleState.value = GoogleSignInState(error = result.message.toString())}
            }

        }
    }

    fun loginUser(email:String, password:String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{
                result ->
            when(result){
                is Resource.Success ->{_loginState.send(AuthState(data = "LogIn Successfully"))}
                is Resource.Loading ->{_loginState.send(AuthState(isLoading = true))}
                is Resource.Error ->{_loginState.send(AuthState(error = result.message.toString()))}
            }
        }
    }
}