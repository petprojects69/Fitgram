package ru.petprojects69.fitgram.ui.initData

sealed class SaveUserDataState {
    data class Success(val documentId: String) : SaveUserDataState()
    data class Error(val e: Throwable) : SaveUserDataState()
    object Loading : SaveUserDataState()
}