package com.medicalmanager.data.repository

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.medicalmanager.core.common.Resource
import com.medicalmanager.domain.repository.NotificationRepoInt
import com.medicalmanager.presentation.utils.buildNotification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val context:Context
) :NotificationRepoInt{
    /*
    fun showNotification(title:String,content:String){
        val notificationManager = ContextCompat.getSystemService(context,NotificationManager::class.java) as NotificationManager
        val notification = buildNotification(context, title, content)
        notificationManager.notify(0,notification)
    }


     */
    override suspend fun triggerNotification(selectedDate: String): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading())
            val title = "Appointment Scheduled"
            val content = "You have successfully booked an appointment on $selectedDate"
            showNotification(title,content)
            emit(Resource.Success(Unit))
        }.catch {
           emit(Resource.Error(it.message.toString()))
        }
    }
    private fun showNotification(title:String ,content:String){
       val notificationBuilder =  NotificationCompat.Builder
    }
}