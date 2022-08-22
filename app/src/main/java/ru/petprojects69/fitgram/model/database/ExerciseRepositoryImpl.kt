package ru.petprojects69.fitgram.model.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercise.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.PowerExerciseEntity

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    val allPowerExercise: Flow<MutableList<PowerExerciseEntity>> =
        appDatabaseDao.getAllPowerExercises()

    val allAerobicExercise: Flow<MutableList<AerobicExerciseEntity>> =
        appDatabaseDao.getAllAerobicExercises()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertPowerExercise(exercise: PowerExerciseEntity) {
        appDatabaseDao.insertPowerExercise(exercise)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertAerobicExercise(exercise: AerobicExerciseEntity) {
        appDatabaseDao.insertAerobicExercise(exercise)
    }
}