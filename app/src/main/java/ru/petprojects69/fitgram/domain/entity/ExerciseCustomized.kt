package ru.petprojects69.fitgram.domain.entity

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

// TODO переписать правильно, пока костыль

open class ExerciseCustomized(
    val exPower: PowerExerciseEntity?,
    val exAerobic: AerobicExerciseEntity?,
    val duration: Int?,
    val sets: Int?,
    val reps: Int?
)

data class AerobicExerciseCustomized(
    val exInitial: AerobicExerciseEntity,
    val exDuration: Int
) : ExerciseCustomized(
    exAerobic = exInitial,
    duration = exDuration,
    sets = null,
    reps = null,
    exPower = null
)

data class PowerExerciseCustomized(
    val exInitial: PowerExerciseEntity,
    val exSets: Int,
    val exReps: Int
) : ExerciseCustomized(
    exPower = exInitial,
    sets = exSets,
    reps = exReps,
    exAerobic = null,
    duration = null
)
