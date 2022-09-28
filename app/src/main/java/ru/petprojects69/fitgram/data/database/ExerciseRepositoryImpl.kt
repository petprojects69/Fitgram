package ru.petprojects69.fitgram.data.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertTraining(training: TrainingEntity) {
        appDatabaseDao.insertTraining(training)
    }

    override suspend fun getAllTraining(): Flow<MutableList<TrainingEntity>> =
        appDatabaseDao.getAllTraining()

    override suspend fun getAllEx(): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.getAllEx()

    override suspend fun getAllAerobicEx(): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.getAllAerobicEx()

    override suspend fun getAllPowerEx(): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.getAllPowerEx()

    override suspend fun getExerciseForId(idExercise: String): Flow<ExerciseEntity> =
        appDatabaseDao.getExerciseForId(idExercise)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertEx(ex: ExerciseEntity) {
        appDatabaseDao.insertEx(ex)
    }

    override suspend fun findExercise(exerciseName: String): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.findExercise(exerciseName)

}