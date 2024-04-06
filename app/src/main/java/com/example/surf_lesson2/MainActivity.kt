package com.example.surf_lesson2

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.surf_lesson2.databinding.ActivityMainBinding
import com.example.surf_lesson2.functions.receiveDataFromSecretKeyContentProvider
import com.example.surf_lesson2.receiver.SurfBroadcastReceiver
import com.example.surf_lesson2.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        const val CONTENT_PROVIDER_KEY = "cp_key"
        const val BROADCAST_KEY = "b_key"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var surfBroadcastReceiver: SurfBroadcastReceiver
    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel = MainViewModel()

        val receivedSecretKeyFromContentProvider = receiveDataFromSecretKeyContentProvider(
            contentResolver
        )

        surfBroadcastReceiver = SurfBroadcastReceiver { value ->
            viewModel.setBroadcastMessage(value)
        }

        val filter = IntentFilter("ru.shalkoff.vsu_lesson2_2024.SURF_ACTION")
        registerReceiver(surfBroadcastReceiver, filter, RECEIVER_EXPORTED)

        binding.apply {

            // Кнопка для отображения сообщения из Content Provider
            receiveSecretKeyButton.setOnClickListener {
                receivedSecretKeyFromContentProvider?.let {
                    viewModel.setSecretKeyMessage(it)
                }
                Toast.makeText(
                    this@MainActivity,
                    viewModel.getSecretKeyMessage(),
                    Toast.LENGTH_SHORT
                ).show()
            }

            // Кнопка для отображения сообщения из Broadcast
            receiveBroadcastMessageButton.setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    viewModel.getBroadcastMessage(),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(CONTENT_PROVIDER_KEY, viewModel.getSecretKeyMessage())
        outState.putString(BROADCAST_KEY, viewModel.getBroadcastMessage())
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle
    ) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString(CONTENT_PROVIDER_KEY)?.let {
            viewModel.setSecretKeyMessage(it)
        }
        viewModel.setBroadcastMessage(
            savedInstanceState.getString(BROADCAST_KEY).toString()
        )
    }

    override fun onDestroy() {
        unregisterReceiver(surfBroadcastReceiver)
        super.onDestroy()
    }

}