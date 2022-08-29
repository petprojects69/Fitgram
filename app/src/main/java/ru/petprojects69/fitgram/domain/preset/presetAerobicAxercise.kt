package ru.petprojects69.fitgram.domain.preset

import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.ExerciseEntity

val presetAerobicExercise = listOf(
    AerobicExerciseEntity(
        exercise = ExerciseEntity(
            name = "Бег трусцой",
            description = "Главное - дыхание"
        ),
    ),
    AerobicExerciseEntity(
        exercise = ExerciseEntity(
            name = "Плавание",
            description = "Плавать нужно как рыбка!!"
        ))
)