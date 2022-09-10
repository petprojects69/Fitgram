package ru.petprojects69.fitgram.data.preset.exercises

import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType

val running = ExerciseEntity(
    type = ExerciseType.AEROBIC,
    name = "Бег за пивом",
    description = "Восстанавливает электролиты",
    location = "КБ",
    muscleGroup = "Душа",
    poster = R.drawable.ex_running,
)