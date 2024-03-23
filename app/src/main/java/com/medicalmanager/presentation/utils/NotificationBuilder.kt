package com.medicalmanager.presentation.utils

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.medicalmanager.R
import com.medicalmanager.presentation.main.MainActivity

fun buildNotification(context:Context,title:String,content:String):Notification{
    val intent = Intent(context,MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE)

    return NotificationCompat.Builder(context,"channel_id")
        .setContentTitle(title)
        .setContentText(content)
        .setSmallIcon(R.drawable.ic_rounded_notification)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()
}