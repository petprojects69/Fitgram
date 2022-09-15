package ru.petprojects69.fitgram.domain.entity

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

data class ExerciseCustomized(
    val exInitial: ExerciseEntity,
    var duration: Int? = null,
    var sets: Int? = null,
    var reps: Int? = null,
)
