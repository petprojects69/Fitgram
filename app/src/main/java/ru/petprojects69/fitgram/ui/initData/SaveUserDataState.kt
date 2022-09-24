package ru.petprojects69.fitgram.ui.initData

import ru.petprojects69.fitgram.domain.entity.UserEntity

sealed class SaveUserDataState {
    object EmptyData : SaveUserDataState()
    data class DataReceived(val userEntity: UserEntity?) : SaveUserDataState()
    data class Success(val documentId: String) : SaveUserDataState()
    data class Error(val e: Throwable) : SaveUserDataState()
    object Loading : SaveUserDataState()
}