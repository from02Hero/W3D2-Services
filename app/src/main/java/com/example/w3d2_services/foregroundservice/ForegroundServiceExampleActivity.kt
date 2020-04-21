package com.example.w3d2_services.foregroundservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.w3d2_services.R
import kotlinx.android.synthetic.main.activity_foreground_service_example.*

class ForegroundServiceExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service_example)

        buttonStart.setOnClickListener {
            ForegroundService.startService(this, "Foreground Service is running...")
        }
        buttonStop.setOnClickListener {
            ForegroundService.stopService(this)
        }
    }
}
