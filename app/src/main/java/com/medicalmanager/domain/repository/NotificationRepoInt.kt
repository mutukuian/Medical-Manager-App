package com.medicalmanager.domain.repository

import com.medicalmanager.core.common.Resource
import kotlinx.coroutines.flow.Flow

interface NotificationRepoInt {
    suspend fun triggerNotification(selectedDate:String): Flow<Resource<Unit>>
}