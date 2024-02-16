package com.medicalmanager.presentation.main

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.repository.AuthRepository
import com.medicalmanager.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val repository: AuthRepository
) :ViewModel(){
    var isLoading = MutableStateFlow(true)
        private set

    var startDestination = MutableStateFlow(Screen.LogInScreen.route)
        private set

    init {
        viewModelScope.launch (Dispatchers.IO){
            repository.loginUser(email= "", password = "").collect{result ->
                when (result){
                    is Resource.Success->{
                        startDestination.value = Screen.HomeScreen.route
                    }
                    is Resource.Loading->{
                        isLoading.value = false
                    }
                    is Resource.Error->{
                      //  Toast.makeText()
                    }
                }
            }
        }
    }
}