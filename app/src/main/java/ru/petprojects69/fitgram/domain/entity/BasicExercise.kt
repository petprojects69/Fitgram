package ru.petprojects69.fitgram.domain.entity

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

// TODO переписать так, чтобы был доступ к полям exerciseList: MutableList<BasicExercise> в TrainingEntity

open class BasicExercise(
    val name: String?,
)

data class AerobicEx(
    val exercise: AerobicExerciseEntity,
    val duration: Int?
) : BasicExercise(name = exercise.exercise.name)

data class PowerEx(
    val exercise: PowerExerciseEntity,
    val sets: Int,
    val reps: Int
) : BasicExercise(name = exercise.exercise.name)
