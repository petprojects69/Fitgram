package ru.petprojects69.fitgram.data.preset.aerobic

import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.ExerciseEntity

val running = AerobicExerciseEntity(
    exercise = ExerciseEntity(
        name = "Бег за пивом",
        description = "Восстанавливает электролиты",
        location = "КБ",
        muscleGroup = "Душа"
    )
)