package com.example.tema2_titescuandrei.helpers.extensions

import android.util.Log

fun String.logErrorMessage(tag: String = "Animals") {
    Log.e(tag, this)
}