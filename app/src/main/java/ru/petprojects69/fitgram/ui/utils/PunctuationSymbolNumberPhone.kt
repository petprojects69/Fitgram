package ru.petprojects69.fitgram.ui.utils

import android.text.Editable
import com.google.android.material.textfield.TextInputEditText

private fun addFirstBracket(text: String): Editable {
    val textSize = text.length
    val first = text.dropLast(1)
    val second = text.drop(textSize - 1)
    return "$first ($second".toEditable()
}

private fun addSecondBracket(text: String): Editable {
    val textSize = text.length
    val first = text.dropLast(1)
    val second = text.drop(textSize - 1)
    return "$first) $second".toEditable()
}

private fun addHyphen(text: String): Editable {
    val textSize = text.length
    val first = text.dropLast(1)
    val second = text.drop(textSize - 1)
    return "$first-$second".toEditable()
}

private fun setTextWithFirstBracket(
    view: TextInputEditText,
    text: CharSequence?
) {
    view.text = addFirstBracket(text.toString())
    view.setSelection(text?.length!! + 2)
}

private fun setTextWithSecondBracket(
    view: TextInputEditText,
    text: CharSequence?
) {
    view.text = addSecondBracket(text.toString())
    view.setSelection(text?.length!! + 2)
}

private fun setTextWithHyphen(
    view: TextInputEditText,
    text: CharSequence?
) {
    view.text = addHyphen(text.toString())
    view.setSelection(text?.length!! + 1)
}

private fun setTextWithoutSymbol(
    view: TextInputEditText,
    text: CharSequence?,
    count: Int
) {
    view.text = text.toString().dropLast(count).toEditable()
    view.setSelection(text?.length!! - count)
}

fun punctuationSymbolNumberPhone(
    view: TextInputEditText,
    text: CharSequence?,
    start: Int,
    end: Int
) {
    if (start == 2 && end == 0) {
        setTextWithFirstBracket(view, text)
    }
    if (start == 7 && end == 0) {
        setTextWithSecondBracket(view, text)
    }
    if (start == 12 && end == 0) {
        setTextWithHyphen(view, text)
    }
    if (start == 15 && end == 0) {
        setTextWithHyphen(view, text)
    }
    if (start == 16 && end == 1) {
        setTextWithoutSymbol(view, text, 1)
    }
    if (start == 13 && end == 1) {
        setTextWithoutSymbol(view, text, 1)
    }
    if (start == 9 && end == 1) {
        setTextWithoutSymbol(view, text, 2)
    }
    if (start == 4 && end == 1) {
        setTextWithoutSymbol(view, text, 2)
    }
}