package ru.petprojects69.fitgram

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        showNotification(PUSH_TITLE, PUSH_MESSAGE)
    }

//    private fun showNotification(title: String, message: String) {
//        val notificationBuilder =
//            NotificationCompat.Builder(applicationContext, CHANNEL_ID).apply {
//                setSmallIcon(R.drawable.ic_baseline_add_24)
//                setContentTitle(title)
//                setStyle(
//                    NotificationCompat.BigTextStyle()
//                        .bigText (message)
//                )
//                priority = NotificationCompat.PRIORITY_DEFAULT
//            }
//        val notificationManager =
//            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createNotificationChannel(notificationManager)
//        }
//        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun createNotificationChannel(notificationManager: NotificationManager) {
//        val name = "Channel name"
//        val descriptionText = "Channel description"
//        val importance = NotificationManager.IMPORTANCE_DEFAULT
//        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//            description = descriptionText
//        }
//        notificationManager.createNotificationChannel(channel)
//    }
//
//    override fun onNewToken(token: String) {
//    }
//
//    companion object {
//        private const val PUSH_TITLE = "Не забудь о тренировке!"
//        private const val PUSH_MESSAGE = "Пока вы бухаете - я тренируюсь. А потом тоже бухаю!"
//        private const val CHANNEL_ID = "channel_id"
//        private const val NOTIFICATION_ID = 37
//    }
}