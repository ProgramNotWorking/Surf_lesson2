package com.example.surf_lesson2.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var secretKeyMessage: String? = null
    private var broadcastMessage: String? = null

    fun getSecretKeyMessage(): String = secretKeyMessage ?: "Nothing"

    fun setSecretKeyMessage(value: String) {
        secretKeyMessage = value
    }

    fun getBroadcastMessage(): String = broadcastMessage ?: "Nothing"

    fun setBroadcastMessage(value: String?) {
        broadcastMessage = value
    }

}