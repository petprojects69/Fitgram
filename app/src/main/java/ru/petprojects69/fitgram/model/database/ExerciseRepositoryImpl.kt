package ru.petprojects69.fitgram.model.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.domain.ExerciseRepository

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    val allPowerExercise: Flow<MutableList<PowerExerciseEntity>> = appDatabaseDao.getAllPowerExercises()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertPowerExercise(powerExercise: PowerExerciseEntity) {
        appDatabaseDao.insertPowerExercise(powerExercise)
    }
}