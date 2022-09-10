package ru.petprojects69.fitgram.data.preset.training

import ru.petprojects69.fitgram.data.preset.exercises.running
import ru.petprojects69.fitgram.data.preset.power.*
import ru.petprojects69.fitgram.domain.entity.AerobicExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.PowerExerciseCustomized

val presetTrainings = listOf(
    TrainingEntity(
        label = "Тренировка на мощь",
        exerciseList = mutableListOf(
            PowerExerciseCustomized(exInitial =  deadLift, exSets = 3, exReps = 5),
            PowerExerciseCustomized(exInitial = benchPress, exSets = 5, exReps = 10),
            PowerExerciseCustomized(exInitial = squat, exSets = 4, exReps = 10),
            PowerExerciseCustomized(exInitial = militaryPress, exSets = 3, exReps = 8),
            PowerExerciseCustomized(exInitial = bicepsCurl, exSets = 2, exReps = 15)
        )
    ),
    TrainingEntity(
        label = "Тренировка на хлесткость",
        exerciseList = mutableListOf(
            PowerExerciseCustomized(exInitial = pushUps, exSets = 4, exReps = 10),
            PowerExerciseCustomized(exInitial = pullUps, exSets = 3, exReps = 8),
            AerobicExerciseCustomized(exInitial = running, exDuration = 300)
        )
    )
)
