package ru.petprojects69.fitgram.domain

import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

interface ExerciseRepository {
    suspend fun insertTraining(training: Training)

    suspend fun insertPowerEx(ex: PowerExerciseEntity)
    suspend fun insertAerobicEx(ex: AerobicExerciseEntity)
}