package ru.petprojects69.fitgram.data.preset.aerobic

import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

val running = AerobicExerciseEntity(
    exercise = ExerciseEntity(
        name = "Бег за пивом",
        description = "Восстанавливает электролиты",
        location = "КБ",
        muscleGroup = "Душа",
        poster = R.drawable.ex_running,
    )
)