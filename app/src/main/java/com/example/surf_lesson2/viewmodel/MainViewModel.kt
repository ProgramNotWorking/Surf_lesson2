package com.example.surf_lesson2.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var contentProviderMessage: String? = null
    private var broadcastMessage: String? = null

    fun getSecretKeyMessage(): String = contentProviderMessage ?: "Nothing"

    fun setSecretKeyMessage(value: String) {
        contentProviderMessage = value
    }

    fun getBroadcastMessage(): String = broadcastMessage ?: "Nothing"

    fun setBroadcastMessage(value: String?) {
        broadcastMessage = value
    }

}