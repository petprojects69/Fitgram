package ru.petprojects69.fitgram.domain.entity

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

// TODO переписать так, чтобы был доступ к полям exerciseList: MutableList<BasicExercise> в TrainingEntity

open class TrainingExercise(
    val name: String?,
)

data class TrainingAerobicExercise(
    val exercise: AerobicExerciseEntity,
    val duration: Int?
) : TrainingExercise(name = exercise.exercise.name)

data class TrainingPowerExercise(
    val exercise: PowerExerciseEntity,
    val sets: Int,
    val reps: Int
) : TrainingExercise(name = exercise.exercise.name)
