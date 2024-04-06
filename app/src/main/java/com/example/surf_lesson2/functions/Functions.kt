package com.example.surf_lesson2.functions

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri

@SuppressLint("Range")
fun receiveDataFromSecretKeyContentProvider(
    resolver: ContentResolver
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

    return text
}