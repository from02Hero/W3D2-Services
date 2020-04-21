package com.example.w3d2_services.intentservice

import android.app.Activity
import android.app.IntentService
import android.content.Intent
import java.util.logging.Logger

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions and extra parameters.
 */
class MyIntentService : IntentService("MyIntentService") {

    val log = Logger.getLogger(MyIntentService::class.java.name)
    private var result = Activity.RESULT_CANCELED

    companion object {
        val URL = "urlpath"
        val FILENAME = "filename"
        val FILEPATH = "filepath"
        val RESULT = "result"
        val NOTIFICATION = "com.example.w3d2_services.intentservice.receiver"
    }

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val data = intent.getStringExtra("data")
            log.info("Message is : $data")
            result = Activity.RESULT_OK;
            publishResults("output.getAbsolutePath()", result)
        }
    }

    private fun publishResults(outputPath: String, result: Int) {
        val intent = Intent(NOTIFICATION)
        intent.putExtra(FILEPATH, outputPath)
        intent.putExtra(RESULT, result)
        sendBroadcast(intent)
    }

}