package ru.petprojects69.fitgram.domain.preset

import ru.petprojects69.fitgram.domain.entity.AerobicEx
import ru.petprojects69.fitgram.domain.entity.AerobicType
import ru.petprojects69.fitgram.domain.entity.PowerEx
import ru.petprojects69.fitgram.domain.entity.Training

val presetTrainings = listOf(
    Training(
        label = "Тренировка ОФП v.1",
        exerciseList = mutableListOf(
            AerobicEx(labelAerobic = "Бег", type = AerobicType.RUN),
            PowerEx(labelPower = "Жим"),
            PowerEx(labelPower = "Тяга"),
            PowerEx(labelPower = "Присед"),
        )
    ),
    Training(
        label = "Тренировка ОФП v.2",
        exerciseList = mutableListOf(
            AerobicEx(labelAerobic = "Плавание"),
            PowerEx(labelPower = "Жим"),
            PowerEx(labelPower = "Тяга", count = 5)
        )
    )
)