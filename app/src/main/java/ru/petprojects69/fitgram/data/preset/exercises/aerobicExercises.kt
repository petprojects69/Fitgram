package ru.petprojects69.fitgram.data.preset.exercises

import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType

val running = ExerciseEntity(
    type = ExerciseType.AEROBIC,
    name = "Бег",
    description = "Отличное упражнение для укрепления сердечно-сосудистой стистемы и борьбы с лишним весом",
    location = "Улица",
    muscleGroup = "Все тело",
    poster = R.drawable.ex_running,
)

val jumping = ExerciseEntity(
    type = ExerciseType.AEROBIC,
    name = "Прыжки",
    description = "Развивает взрывную силу ног",
    location = "Улица",
    muscleGroup = "Ноги"
)

val swimming = ExerciseEntity(
    type = ExerciseType.AEROBIC,
    name = "Плавание",
    description = "Укрепляет все тело. К тому же очень полезно для спины и суставов",
    location = "Бассейн",
    muscleGroup = "Все тело"
)