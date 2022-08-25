package ru.petprojects69.fitgram.domain.preset

import ru.petprojects69.fitgram.domain.entity.exercises.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

val presetPowerExercise = listOf(
    PowerExerciseEntity(
        exercise = ExerciseEntity(
            name = "Отжимания"
        ),
        numberOfRepetitions = 25
    ),
    PowerExerciseEntity(
        exercise = ExerciseEntity(
            name = "Приседания"
        ),
        numberOfRepetitions = 10
    )
)