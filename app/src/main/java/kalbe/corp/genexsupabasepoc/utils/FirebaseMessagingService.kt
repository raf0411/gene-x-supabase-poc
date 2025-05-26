package kalbe.corp.genexsupabasepoc.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kalbe.corp.genexsupabasepoc.MainActivity
import kalbe.corp.genexsupabasepoc.R
import kalbe.corp.genexsupabasepoc.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private val job = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.IO + job)

    private val userRepository = UserRepository()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        remoteMessage.notification?.let { notification ->
            Log.d(TAG, "Message Notification Title: ${notification.title}")
            Log.d(TAG, "Message Notification Body: ${notification.body}")

            sendLocalNotification(notification.title, notification.body)
        }
    }

    private fun sendLocalNotification(title: String?, messageBody: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        val channelId = "fcm_default_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title ?: "FCM Message")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Default FCM Channel",
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }

    private fun sendRegistrationToServer(token: String?) {
        if (token == null) {
            Log.w(TAG, "FCM token is null, cannot send to server.")
            return
        }

//        serviceScope.launch {
//            try {
//                val userId = supabaseClient.auth.currentUserOrNull()?.id
//                if (userId != null) {
//                    val success = userRepository.updateUserFcmToken(userId, token)
//                    if (success) {
//                        Log.d(TAG, "FCM Token update successful via repository.")
//                    } else {
//                        Log.w(TAG, "FCM Token update failed via repository.")
//                    }
//                } else {
//                    Log.w(TAG, "User not logged in, cannot send FCM token from service.")
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "Error in sendRegistrationToServer scope", e)
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}