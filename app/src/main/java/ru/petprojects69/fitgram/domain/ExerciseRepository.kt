package ru.petprojects69.fitgram.domain

import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.domain.entity.Training

interface ExerciseRepository {
    suspend fun insertPowerExercise(training: Training)
}