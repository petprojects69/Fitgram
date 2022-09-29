package ru.petprojects69.fitgram.domain.usecase

interface ItemActionCallback {
    // TODO в параметры передать объект Тренировка
    fun delete()
    fun update()
    fun <T>itemClick(training: T)
}