package ru.petprojects69.fitgram.data.preset.training

import ru.petprojects69.fitgram.data.preset.exercises.*
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

val presetTrainings = listOf(
    TrainingEntity(
        label = "Силовая тренировка",
        exerciseList = mutableListOf(
            ExerciseCustomized(exInitial = deadLift, sets = 3, reps = 5),
            ExerciseCustomized(exInitial = benchPress, sets = 5, reps = 10),
            ExerciseCustomized(exInitial = squat, sets = 4, reps = 10),
            ExerciseCustomized(exInitial = militaryPress, sets = 3, reps = 8),
            ExerciseCustomized(exInitial = bicepsCurl, sets = 2, reps = 15)
        )
    ),
    TrainingEntity(
        label = "Тренировка на выносливость",
        exerciseList = mutableListOf(
            ExerciseCustomized(exInitial = pushUps, sets = 4, reps = 10),
            ExerciseCustomized(exInitial = pullUps, sets = 3, reps = 8),
            ExerciseCustomized(exInitial = running, duration = 300)
        )
    ),
    TrainingEntity(
        label = "ОФП",
        exerciseList = mutableListOf(
            ExerciseCustomized(exInitial = running, duration = 100),
            ExerciseCustomized(exInitial = benchPress, sets = 4, reps = 12),
            ExerciseCustomized(exInitial = pullUps, sets = 5),
            ExerciseCustomized(exInitial = squat, sets = 3, reps = 15),
            ExerciseCustomized(exInitial = jumping, duration = 10)
        )
    )
)
