package ru.petprojects69.fitgram.model.database.repo

import ru.petprojects69.fitgram.model.database.entity.PowerExerciseEntity

interface ExerciseRepository {
    suspend fun insertPowerExercise(powerExercise: PowerExerciseEntity)
}