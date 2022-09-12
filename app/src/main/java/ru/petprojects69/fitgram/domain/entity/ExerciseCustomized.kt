package ru.petprojects69.fitgram.domain.entity

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

// TODO переписать правильно, пока костыль

open class ExerciseCustomized(
    val exerciseInitial: ExerciseEntity,
    val duration: Int?,
    val sets: Int?,
    val reps: Int?,
)

data class AerobicExerciseCustomized(
    val exInitial: ExerciseEntity,
    val exDuration: Int,
) : ExerciseCustomized(
    exerciseInitial = exInitial,
    duration = exDuration,
    sets = null,
    reps = null
)

data class PowerExerciseCustomized(
    val exInitial: ExerciseEntity,
    val exSets: Int,
    val exReps: Int,
) : ExerciseCustomized(
    exerciseInitial = exInitial,
    sets = exSets,
    reps = exReps,
    duration = null
)
