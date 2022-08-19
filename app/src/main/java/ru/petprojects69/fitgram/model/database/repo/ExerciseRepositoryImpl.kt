package ru.petprojects69.fitgram.model.database.repo

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.model.database.AppDatabaseDao
import ru.petprojects69.fitgram.model.database.entity.PowerExerciseEntity

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    val allPowerExercise: Flow<List<PowerExerciseEntity>> = appDatabaseDao.getAllPowerExercises()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertPowerExercise(powerExercise: PowerExerciseEntity) {
        appDatabaseDao.insertPowerExercise(powerExercise)
    }
}