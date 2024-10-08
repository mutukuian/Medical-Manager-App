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
class LogOutViewModel @Inject constructor(
    private val repository:AuthRepository
) :ViewModel() {


    private val _logOutState = Channel<AuthState>()
    val logOutState = _logOutState.receiveAsFlow()

    fun signOut() = viewModelScope.launch {
        repository.signOut().collect { result ->
            when (result) {
                is Resource.Success -> {
                    _logOutState.send(AuthState(data = "Log Out Successfully"))
                }

                is Resource.Loading -> {
                    _logOutState.send(AuthState(isLoading = true))
                }

                is Resource.Error -> {
                    _logOutState.send(AuthState(error = result.message.toString()))
                }
            }
        }
    }
}