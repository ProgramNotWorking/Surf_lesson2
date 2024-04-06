package com.example.surf_lesson2.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class SurfBroadcastReceiver : BroadcastReceiver() {

    private var message: String? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "ru.shalkoff.vsu_lesson2_2024.SURF_ACTION") {
            message = intent.getStringExtra("message")
            Toast.makeText(
                context, message, Toast.LENGTH_LONG
            ).show()
            Log.d("broadcast", message ?: "Nothing")
        }
    }

    fun getMessage(): String? {
        return message
    }

}