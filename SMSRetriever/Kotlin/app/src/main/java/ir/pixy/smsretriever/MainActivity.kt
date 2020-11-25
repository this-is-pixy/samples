package ir.pixy.smsretriever

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.credentials.Credential
import com.google.android.gms.auth.api.credentials.Credentials
import com.google.android.gms.auth.api.credentials.HintRequest
import com.google.android.gms.auth.api.phone.SmsRetriever

const val RESOLVE_HINT = 10

class MainActivity : AppCompatActivity() {

    private val context: Context by lazy {
        this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun requestHint() {
        val hintRequest = HintRequest.Builder().apply {
            setPhoneNumberIdentifierSupported(true)
        }

        val credentialIntent = Credentials.getClient(this).getHintPickerIntent(hintRequest.build())
        startIntentSenderForResult(credentialIntent.intentSender, RESOLVE_HINT, null, 0, 0, 0)
    }

    fun smsRetriever() {
        val smsRetriever = SmsRetriever.getClient(context)
        val task = smsRetriever.startSmsRetriever()

        task.addOnSuccessListener {
            // Successfully started retriever, expect broadcast intent
            // ...
        }

        task.addOnFailureListener {
            // Failed to start retriever, inspect Exception for more details
            // ...
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESOLVE_HINT)
            if (resultCode == Activity.RESULT_OK) {
                val credential = data?.getParcelableExtra<Credential>(Credential.EXTRA_KEY)

                // Selected phone number
                val phone = credential?.id;
            }
    }
}
