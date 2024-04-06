package com.example.surf_lesson2.functions

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri

// Функция для получения сообщения из Content Provider
@SuppressLint("Range")
fun receiveDataFromSecretKeyContentProvider(
    resolver: ContentResolver, operation: (String?) -> Unit
): String? {
    val uri = Uri.parse("content://dev.surf.android.provider/text")
    val cursor = resolver.query(
        uri,
        null,
        null,
        null,
        null
    )

    var text: String? = null

    cursor?.use {
        if (it.moveToFirst()) {
            text = it.getString(it.getColumnIndex("text"))
        }
    }
    cursor?.close()

    operation(text)
    return text
}