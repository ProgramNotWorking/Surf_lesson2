package com.example.surf_lesson2.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

// Класс для получения сообщения из Broadcast
class SurfBroadcastReceiver(val saveOperation: (String) -> Unit) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "ru.shalkoff.vsu_lesson2_2024.SURF_ACTION") {
            val message = intent.getStringExtra("message")
            message?.let { saveOperation(it) }
        }
    }

}