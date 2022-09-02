package ru.petprojects69.fitgram.domain.preset

import ru.petprojects69.fitgram.data.preset.aerobic.running
import ru.petprojects69.fitgram.data.preset.power.*
import ru.petprojects69.fitgram.domain.entity.AerobicEx
import ru.petprojects69.fitgram.domain.entity.PowerEx
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

val presetTrainings = listOf(
    TrainingEntity(
        label = "Тренировка ОФП v.1",
        exerciseList = mutableListOf(
            PowerEx(exercise = militaryPress, sets = 3, reps = 8),
            PowerEx(exercise = benchPress, sets = 5, reps = 10),
            PowerEx(exercise = squat, sets = 4, reps = 10),
            PowerEx(exercise = pullUps, sets = 3, reps = 12),
        )
    ),
    TrainingEntity(
        label = "Тренировка ОФП v.2",
        exerciseList = mutableListOf(
            PowerEx(exercise = deadLift, sets = 4, reps = 5),
            PowerEx(exercise = lunges, sets = 3, reps = 10),
            AerobicEx(exercise = running, duration = 300)
        )
    )
)
