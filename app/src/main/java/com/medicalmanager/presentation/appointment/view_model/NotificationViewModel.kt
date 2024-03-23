package com.medicalmanager.presentation.appointment.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.medicalmanager.core.common.Resource
import com.medicalmanager.data.repository.NotificationRepository
import com.medicalmanager.presentation.appointment.screen.BookingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepo: NotificationRepository
): ViewModel(){


    private val _notificationTriggered: MutableStateFlow<Boolean> = MutableStateFlow(
       false
    )

    val notificationTriggered: StateFlow<Boolean> = _notificationTriggered

    fun triggerNotification(title:String ,content:String){
        notificationRepo.showNotification(title ,content)
    }

    fun triggerNotificationWithDate(title:String ,content:String,date:String)= viewModelScope.launch{
        val notificationContent = "$content on $date"
        notificationRepo.showNotification(title ,notificationContent)
        _notificationTriggered.value = true
    }

    //method to reset triggering status
    fun resetNotificationState(){
        _notificationTriggered.value = false
    }
}