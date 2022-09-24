package ru.petprojects69.fitgram.ui.utils

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import coil.load
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.ui.userProfileFragment.UserSex
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

fun TextInputEditText.customBehaviorHintAndCursor(hint: String) {
    setOnFocusChangeListener { _, hasFocused ->
        if (this.text.isNullOrBlank()) {
            this.gravity = Gravity.START
        } else {
            this.gravity = Gravity.END
        }

        if (hasFocused) {
            this.hint = null
            this.gravity = Gravity.END
        } else {
            this.hint = hint
        }
    }
}

fun TextInputEditText.setDecimalLimit(limit: Int = 2) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            val str = this@setDecimalLimit.text.toString()
            if (str.isEmpty()) return

            var dotIndex = 0
            str.forEachIndexed { index, c ->
                if (c == '.') {
                    dotIndex = index

                }
            }
            if (str.length > dotIndex + limit + 1) {
                this@setDecimalLimit.text = str.dropLast(1).toEditable()
                this@setDecimalLimit.setSelection(p0.toString().lastIndex)
            }
        }
    })
}

@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.setAvatar(sex: String?) {
    when (sex) {
        UserSex.MAN.sex -> {
            this.load(resources.getDrawable(R.drawable.man_placeholder, null))
        }

        UserSex.WOMAN.sex -> {
            this.load(resources.getDrawable(R.drawable.woman_placeholder, null))
        }

        UserSex.NOT_DEFINED.sex -> {
            this.load(resources.getDrawable(R.drawable.not_defined_placeholder, null))
        }
    }
}