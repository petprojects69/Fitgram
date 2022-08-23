package ru.petprojects69.fitgram.ui

interface ItemActionCallback {
    // TODO в параметры передать объект Тренировка
    fun delete()
    fun update()
    fun itemClick()
}