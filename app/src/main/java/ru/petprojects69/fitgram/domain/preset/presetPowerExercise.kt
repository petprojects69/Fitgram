package ru.petprojects69.fitgram.domain.preset

import ru.petprojects69.fitgram.domain.entity.exercises.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

val presetPowerExercise = listOf(
    PowerExerciseEntity(
        exercise = ExerciseEntity(
            name = "Отжимания",
            description = "Нужно чтобы грудь касалась пола/земли"
        ),
    ),
    PowerExerciseEntity(
        exercise = ExerciseEntity(
            name = "Приседания",
            description = "Колени сгибаем до острого угла"
        ),
    )
)