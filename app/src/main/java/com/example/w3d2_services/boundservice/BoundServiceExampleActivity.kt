package com.example.w3d2_services.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.example.w3d2_services.R
import com.example.w3d2_services.boundservice.BoundService.MyBinder
import kotlinx.android.synthetic.main.activity_bound_service_example.*

class BoundServiceExampleActivity : AppCompatActivity() {

    var mBoundService: BoundService? = null
    var mServiceBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service_example)

        print_timestamp.setOnClickListener {
            if (mServiceBound) {
                timestamp_text.text = mBoundService!!.timestamp
            }
        }
        stop_service.setOnClickListener {
            if (mServiceBound) {
                unbindService(mServiceConnection)
                mServiceBound = false
            }
            val intent = Intent(
                this@BoundServiceExampleActivity,
                BoundService::class.java
            )
            stopService(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, BoundService::class.java)
        startService(intent)
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (mServiceBound) {
            unbindService(mServiceConnection)
            mServiceBound = false
        }
    }

    private val mServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mServiceBound = false
        }

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val myBinder = service as MyBinder
            mBoundService = myBinder.service
            mServiceBound = true
        }
    }
}
