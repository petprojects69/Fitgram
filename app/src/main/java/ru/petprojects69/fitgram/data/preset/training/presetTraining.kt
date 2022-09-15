package ru.petprojects69.fitgram.data.preset.training

import ru.petprojects69.fitgram.data.preset.exercises.*
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

val presetTrainings = listOf(
    TrainingEntity(
        label = "Тренировка на мощь",
        exerciseList = mutableListOf(
            ExerciseCustomized(exInitial = deadLift, sets = 3, reps = 5, duration = null),
            ExerciseCustomized(exInitial = benchPress, sets = 5, reps = 10, duration = null),
            ExerciseCustomized(exInitial = squat, sets = 4, reps = 10, duration = null),
            ExerciseCustomized(exInitial = militaryPress, sets = 3, reps = 8, duration = null),
            ExerciseCustomized(exInitial = bicepsCurl, sets = 2, reps = 15, duration = null)
        )
    ),
    TrainingEntity(
        label = "Тренировка на хлесткость",
        exerciseList = mutableListOf(
            ExerciseCustomized(exInitial = pushUps, sets = 4, reps = 10, duration = null),
            ExerciseCustomized(exInitial = pullUps, sets = 3, reps = 8, duration = null),
            ExerciseCustomized(exInitial = running, duration = 300, sets = null, reps = null)
        )
    )
)
