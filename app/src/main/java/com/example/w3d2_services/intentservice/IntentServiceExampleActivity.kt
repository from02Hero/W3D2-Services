package com.example.w3d2_services.intentservice

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.w3d2_services.R
import kotlinx.android.synthetic.main.activity_intent_service_example.*

class IntentServiceExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_service_example)
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val bundle = intent.extras
            if (bundle != null) {
                val string = bundle.getString(MyIntentService.FILEPATH)
                val resultCode = bundle.getInt(MyIntentService.RESULT)
                if (resultCode == Activity.RESULT_OK) {
                    Toast.makeText(this@IntentServiceExampleActivity,
                        "Download complete. Download URI: $string",
                        Toast.LENGTH_LONG).show()
                    status.text = "Finished"
                } else {
                    Toast.makeText(this@IntentServiceExampleActivity, "Download failed",
                        Toast.LENGTH_LONG).show()
                    status.text = "Failed"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, IntentFilter(
            MyIntentService.NOTIFICATION)
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    fun onClick(view: View?) {
        val intent = Intent(this, MyIntentService::class.java)
        intent.putExtra("data", "Hello Intent service used for handling asynchronous task")
        startService(intent)
        status.text = "Started"
    }
}
