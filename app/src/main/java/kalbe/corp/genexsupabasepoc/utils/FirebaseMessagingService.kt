package kalbe.corp.genexsupabasepoc.utils
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import kalbe.corp.genexsupabasepoc.data.network.supabaseClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
        // TODO: Send this token to your Supabase backend
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "From: ${remoteMessage.from}")

        // Check if message contains a data payload.
        remoteMessage.data.isNotEmpty().let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            // Handle data payload here (e.g., for background notifications or custom data)
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
            // Handle notification payload here (e.g., display a notification if app is in foreground)
            // You might want to build a local notification to display to the user
        }
    }

    private fun sendRegistrationToServer(token: String?) {
         CoroutineScope(Dispatchers.IO).launch {
             try {
                 val userId = supabaseClient.auth.currentUserOrNull()?.id
                 if (userId != null && token != null) {
                     // Assuming you have a 'profiles' table with an 'fcm_token' column
                     // Or a 'device_tokens' table with 'user_id' and 'token' columns
                     supabaseClient.from("users").update(mapOf("fcm_token" to token)){
                         filter {
                             eq("account_id", userId)
                         }
                     }

                     Log.d(TAG, "FCM Token updated in Supabase")
                 }
             } catch (e: Exception) {
                 Log.e(TAG, "Error updating FCM token in Supabase", e)
             }
         }
        Log.d(TAG, "TODO: Implement sendRegistrationToServer($token)")
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}