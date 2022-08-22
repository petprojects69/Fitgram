package ru.petprojects69.fitgram.domain

import ru.petprojects69.fitgram.domain.entity.exercise.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.PowerExerciseEntity

interface ExerciseRepository {
    suspend fun insertPowerExercise(exercise: PowerExerciseEntity)
    suspend fun insertAerobicExercise(exercise: AerobicExerciseEntity)
}