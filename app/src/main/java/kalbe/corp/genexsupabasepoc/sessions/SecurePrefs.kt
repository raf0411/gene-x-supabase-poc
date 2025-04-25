package kalbe.corp.genexsupabasepoc.sessions

import android.content.Context
import android.util.Base64
import androidx.core.content.edit
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class SecurePrefs(context: Context) {
    private val prefs = context.getSharedPreferences("secure_prefs", Context.MODE_PRIVATE)
    private val key: SecretKey = KeystoreManager.getOrCreateSecretKey()

    private companion object {
        const val TRANSFORMATION = "AES/GCM/NoPadding"
        const val IV_SIZE = 12
        const val TAG_SIZE = 128
    }

    fun putEncrypted(keyName: String, plainText: String) {
        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, key)
        }
        val iv = cipher.iv
        val cipherBytes = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))

        val combined = ByteArray(iv.size + cipherBytes.size).also {
            System.arraycopy(iv, 0, it, 0, iv.size)
            System.arraycopy(cipherBytes, 0, it, iv.size, cipherBytes.size)
        }

        val base64 = Base64.encodeToString(combined, Base64.DEFAULT)
        prefs.edit() { putString(keyName, base64) }
    }

    fun getDecrypted(keyName: String): String? {
        val base64 = prefs.getString(keyName, null) ?: return null
        val combined = Base64.decode(base64, Base64.DEFAULT)

        val iv = combined.copyOfRange(0, IV_SIZE)
        val cipherBytes = combined.copyOfRange(IV_SIZE, combined.size)

        val cipher = Cipher.getInstance(TRANSFORMATION).apply {
            init(
                Cipher.DECRYPT_MODE,
                key,
                GCMParameterSpec(TAG_SIZE, iv)
            )
        }

        return try {
            cipher.doFinal(cipherBytes).toString(Charsets.UTF_8)
        } catch (e: Exception) {
            null
        }
    }

    fun clear() {
        prefs.edit() { clear() }
    }
}