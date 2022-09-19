package ru.petprojects69.fitgram.domain

import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.ui.userProfileFragment.UserTarget

fun setCalorieNorm(user: UserEntity): String {
    if (
        user.age == null ||
        user.height == null ||
        user.weight == null ||
        user.target == null
    )
        return "-"
    else {
        var calories: Double?
        val CPA = 1.3
        if (user.sex) {
            // для мужчин
            calories = when (user.age) {
                in 18..30 -> {
                    (0.063 * user.weight + 2.896) * 240 * CPA
                }
                in 31..60 -> {
                    (0.0484 * user.weight + 3.653) * 240 * CPA
                }
                in 60..Int.MAX_VALUE -> {
                    (0.0491 * user.weight + 2.459) * 240 * CPA
                }
                else -> {
                    0.0
                }
            }
        } else {
            // для женщин
            calories = when (user.age) {
                in 18..30 -> {
                    (0.062 * user.weight + 2.036) * 240 * CPA
                }
                in 31..60 -> {
                    (0.034 * user.weight + 3.538) * 240 * CPA
                }
                in 60..Int.MAX_VALUE -> {
                    (0.038 * user.weight + 2.755) * 240 * CPA
                }
                else -> {
                    0.0
                }
            }
        }

        when (user.target) {
            UserTarget.WEIGHT_LOSS.target -> {
                calories *= 0.85
            }
            UserTarget.MASS_SET.target -> {
                calories *= 1.15
            }
        }
        return calories.toInt().toString()
    }
}