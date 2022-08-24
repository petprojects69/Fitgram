package ru.petprojects69.fitgram.model.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    val allPowerExercise: Flow<MutableList<Training>> = appDatabaseDao.getAllTraining()

    val allPowerEx: Flow<MutableList<PowerExerciseEntity>> =
        appDatabaseDao.getAllPowerEx()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertPowerExercise(training: Training) {
        appDatabaseDao.insertTraining(training)
    }

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