package com.medicalmanager.presentation.authentication.auth_view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.repository.AuthRepository
import com.medicalmanager.presentation.authentication.auth_screen.AuthState
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

    fun loginUser(email:String, password:String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{
                result ->
            when(result){
                is Resource.Success ->{_loginState.send(AuthState(data = "Registration Successfully"))}
                is Resource.Loading ->{_loginState.send(AuthState(isLoading = true))}
                is Resource.Error ->{_loginState.send(AuthState(error = result.message.toString()))}
            }
        }
    }
}