package ru.petprojects69.fitgram.ui.userProfileFragment

enum class UserRank(val rank: String) {
    NEWCOMER("Новичек"),
    ATHLETES_FACE("Личинка спортсмена"),
    SPORTSMAN("Спортсмен"),
    EXPERIENCED_ATHLETE("Опытный спортсменюга"),
    TERMINATOR("Терминатор"),
    NATALIA_TRUKHIN("Наталья Трухина"),
    NOT_DEFINED("Не определен")
}

fun setUserRank(state: UserProfileState.Success): UserRank =
    when (state.user.rank) {
        in 0..10 -> {
            UserRank.NEWCOMER
        }
        in 11..50 -> {
            UserRank.ATHLETES_FACE
        }
        in 51..100 -> {
            UserRank.SPORTSMAN
        }
        in 100..200 -> {
            UserRank.EXPERIENCED_ATHLETE
        }
        in 200..300 -> {
            UserRank.TERMINATOR
        }
        in 301..Int.MAX_VALUE -> {
            UserRank.NATALIA_TRUKHIN
        }
        else -> {
            UserRank.NOT_DEFINED
        }
    }