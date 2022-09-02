package ru.petprojects69.fitgram.ui.utils

import android.text.Editable

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)