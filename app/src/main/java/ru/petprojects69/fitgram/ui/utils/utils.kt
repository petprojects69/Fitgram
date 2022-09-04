package ru.petprojects69.fitgram.ui.utils

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun millisToSecond(millis: Long): String {
    return if (millis > 1000) {
        millis.toString().dropLast(3)
    } else {
        "0"
    }
}