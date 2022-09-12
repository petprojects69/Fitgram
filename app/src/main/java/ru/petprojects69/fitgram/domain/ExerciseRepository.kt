package ru.petprojects69.fitgram.domain

import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

interface ExerciseRepository {
    suspend fun insertTraining(training: TrainingEntity)
    suspend fun getAllTraining(): Flow<MutableList<TrainingEntity>>

    suspend fun getAllEx(): Flow<MutableList<ExerciseEntity>>
    suspend fun getAllAerobicEx(): Flow<MutableList<ExerciseEntity>>
    suspend fun getAllPowerEx(): Flow<MutableList<ExerciseEntity>>
    suspend fun insertEx(ex: ExerciseEntity)

    suspend fun findExercise(exerciseName: String): Flow<MutableList<ExerciseEntity>>
    suspend fun getAllPowerEx(): Flow<MutableList<PowerExerciseEntity>>
    suspend fun getAllAerobicEx(): Flow<MutableList<AerobicExerciseEntity>>

    suspend fun getAerobicExForId(idExercise: Int): Flow<AerobicExerciseEntity>
    suspend fun getPowerExForId(idExercise: Int): Flow<PowerExerciseEntity>

    suspend fun insertPowerEx(ex: PowerExerciseEntity)
    suspend fun insertAerobicEx(ex: AerobicExerciseEntity)
}