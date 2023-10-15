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
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel(){

    private val _registerState = Channel<AuthState>()
    val registerState = _registerState.receiveAsFlow()

    fun registerUser(email:String,password:String) = viewModelScope.launch {
        repository.registerUser(email, password).collect{
            result ->
            when(result){
                is Resource.Success ->{_registerState.send(AuthState(data = "Registration Successfully"))}
                is Resource.Loading ->{_registerState.send(AuthState(isLoading = true))}
                is Resource.Error ->{_registerState.send(AuthState(error = result.message.toString()))}
            }
        }
    }
}