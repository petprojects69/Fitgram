package ru.petprojects69.fitgram.ui.utils

import android.text.Editable
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.ui.userProfileFragment.UserTarget
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun millisToSecond(millis: Long): String {
    return if (millis > 1000) {
        millis.toString().dropLast(3)
    } else {
        "0"
    }
}

fun View.showSnack(text: String) {
    val snack = Snackbar.make(this, text, Snackbar.LENGTH_LONG)
    val view = snack.view
    val params = FrameLayout.LayoutParams(view.layoutParams)
    params.apply {
        gravity = Gravity.TOP
        topMargin = 120
        marginStart = 20
        marginEnd = 20
    }
    view.layoutParams = params
    snack.show()
}

suspend fun <T> awaitTask(task: Task<T>): T = suspendCoroutine { continuation ->
    task.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            continuation.resume(task.result)
        } else {
            continuation.resumeWithException(task.exception!!)
        }
    }
    task.addOnFailureListener {
        continuation.resumeWithException(task.exception!!)
    }
}