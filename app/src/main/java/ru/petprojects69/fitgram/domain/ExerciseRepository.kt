package ru.petprojects69.fitgram.domain

import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

interface ExerciseRepository {
    suspend fun insertTraining(training: TrainingEntity)
    suspend fun getAllTraining(): Flow<MutableList<TrainingEntity>>

    suspend fun getAllPowerEx(): Flow<MutableList<PowerExerciseEntity>>
    suspend fun getAllAerobicEx(): Flow<MutableList<AerobicExerciseEntity>>

    suspend fun getAerobicExForId(idExercise: Int): Flow<AerobicExerciseEntity>

    suspend fun insertPowerEx(ex: PowerExerciseEntity)
    suspend fun insertAerobicEx(ex: AerobicExerciseEntity)
}