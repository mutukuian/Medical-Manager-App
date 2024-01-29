package com.medicalmanager.presentation.utils

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
class UserDetailsViewModel @Inject constructor(
  private val repository:AuthRepository
):ViewModel() {

    private val _userState = Channel<AuthState>()
    val userState = _userState.receiveAsFlow()

    fun getUserDetails(email: String) = viewModelScope.launch {
        repository.getUserDetails(email).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _userState.send(AuthState(data = " Successfully"))
                }

                is Resource.Loading -> {
                    _userState.send(AuthState(isLoading = true))
                }

                is Resource.Error -> {
                    _userState.send(AuthState(error = result.message.toString()))
                }
            }
        }
    }
}