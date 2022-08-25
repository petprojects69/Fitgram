package ru.petprojects69.fitgram.domain.preset

import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.ExerciseEntity

val presetAerobicExercise = listOf(
    AerobicExerciseEntity(
        exercise = ExerciseEntity(
            name = "Бег трусцой"
        ),
        leadTime = 25f
    ),
    AerobicExerciseEntity(
        exercise = ExerciseEntity(
            name = "Плавание"
        ),
        leadTime = 100f
    )
)