package com.medicalmanager.data.repository

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import com.medicalmanager.presentation.utils.buildNotification
import javax.inject.Inject

class NotificationRepository @Inject constructor(
    private val context:Context
) {

    fun showNotification(title:String,content:String){
        val notificationManager = ContextCompat.getSystemService(context,NotificationManager::class.java) as NotificationManager
        val notification = buildNotification(context, title, content)
        notificationManager.notify(0,notification)
    }




    /*
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
        if (NotificationManagerCompat.from(context).areNotificationsEnabled()){
       val notificationBuilder =  NotificationCompat.Builder(context,CHANNEL_ID)
           .setContentTitle(title)
           .setContentText(content)
           .setSmallIcon(R.drawable.ic_rounded_notification)
           .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_ID,notificationBuilder.build())
    }else{
            Toast.makeText(context,"Notifications not allowed!!!", Toast.LENGTH_SHORT).show()

        }
    }

    companion object{
        private const val CHANNEL_ID = "channel_id"
        private const val NOTIFICATION_ID = 0
    }

    */

}