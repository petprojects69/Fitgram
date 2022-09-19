package ru.petprojects69.fitgram.ui.userProfileFragment

import ru.petprojects69.fitgram.domain.entity.UserEntity

sealed class UserProfileState{
    object Loading: UserProfileState()
    data class Success(val user: UserEntity) : UserProfileState()
    data class Error(val e: Throwable): UserProfileState()
    object SignOut: UserProfileState()
    object NotUser: UserProfileState()
}
