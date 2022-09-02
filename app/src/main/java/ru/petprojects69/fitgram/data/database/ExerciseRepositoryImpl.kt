package ru.petprojects69.fitgram.data.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertTraining(training: TrainingEntity) {
        appDatabaseDao.insertTraining(training)
    }

    override suspend fun getAllTraining(): Flow<MutableList<TrainingEntity>> =
        appDatabaseDao.getAllTraining()

    override suspend fun getAllPowerEx(): Flow<MutableList<PowerExerciseEntity>> =
        appDatabaseDao.getAllPowerEx()

    override suspend fun getAllAerobicEx(): Flow<MutableList<AerobicExerciseEntity>> =
        appDatabaseDao.getAllAerobicEx()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertPowerEx(ex: PowerExerciseEntity) {
        appDatabaseDao.insertPowerEx(ex)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertAerobicEx(ex: AerobicExerciseEntity) {
        appDatabaseDao.insertAerobicEx(ex)
    }
}