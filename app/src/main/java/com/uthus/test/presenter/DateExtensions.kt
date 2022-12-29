package com.uthus.test.presenter

import java.util.*

fun Date.countDownTime(): String {
    val diff: Long = this.time - Date().time
    val seconds = ((diff / 1000) % 60).toInt()
    val minutes = (diff / (1000 * 60) % 60).toInt()
    val hours = (diff / (1000 * 60 * 60) % 24).toInt()
    val day = (diff / (1000 * 60 * 60 * 24)).toInt()
    return String.format("%02d:%02d:%02d, %d(day)", hours, minutes, seconds, day)
}