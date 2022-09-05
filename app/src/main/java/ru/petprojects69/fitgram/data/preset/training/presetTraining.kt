package ru.petprojects69.fitgram.domain.preset

import ru.petprojects69.fitgram.data.preset.aerobic.running
import ru.petprojects69.fitgram.data.preset.power.*
import ru.petprojects69.fitgram.domain.entity.TrainingAerobicExercise
import ru.petprojects69.fitgram.domain.entity.TrainingPowerExercise
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

val presetTrainings = listOf(
    TrainingEntity(
        label = "Тренировка ОФП v.1",
        exerciseList = mutableListOf(
            TrainingPowerExercise(exercise = militaryPress, sets = 3, reps = 8),
            TrainingPowerExercise(exercise = benchPress, sets = 5, reps = 10),
            TrainingPowerExercise(exercise = squat, sets = 4, reps = 10),
            TrainingPowerExercise(exercise = pullUps, sets = 3, reps = 12),
        )
    ),
    TrainingEntity(
        label = "Тренировка ОФП v.2",
        exerciseList = mutableListOf(
            TrainingPowerExercise(exercise = deadLift, sets = 4, reps = 5),
            TrainingPowerExercise(exercise = lunges, sets = 3, reps = 10),
            TrainingAerobicExercise(exercise = running, duration = 300)
        )
    )
)
