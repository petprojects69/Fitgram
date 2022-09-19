package ru.petprojects69.fitgram.domain.usecase

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

    suspend fun getExerciseForId(idExercise: Int): Flow<ExerciseEntity>
    suspend fun removeExerciseForId(idExercise: Int)
}