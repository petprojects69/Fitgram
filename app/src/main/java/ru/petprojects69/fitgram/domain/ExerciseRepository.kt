package ru.petprojects69.fitgram.domain

import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity

interface ExerciseRepository {
    suspend fun insertPowerExercise(powerExercise: PowerExerciseEntity)
}