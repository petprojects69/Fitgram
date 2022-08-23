package ru.petprojects69.fitgram.model.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flow
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercise.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.ExerciseList
import ru.petprojects69.fitgram.domain.entity.exercise.ExerciseList.*
import ru.petprojects69.fitgram.domain.entity.exercise.PowerExerciseEntity

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    val allPowerExercise: Flow<MutableList<PowerExerciseEntity>> =
        appDatabaseDao.getAllPowerExercises()

    val allAerobicExercise: Flow<MutableList<AerobicExerciseEntity>> =
        appDatabaseDao.getAllAerobicExercises()

    val allExercise: Flow<MutableList<ExerciseList>> =
        allPowerExercise as Flow<MutableList<ExerciseList>>

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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertExercise(exerciseItem: ExerciseList) {
        when (exerciseItem) {
            is AerobicExercise -> {
                appDatabaseDao.insertAerobicExercise(exerciseItem.exercise)
            }
            is PowerExercise -> {
                appDatabaseDao.insertPowerExercise(exerciseItem.exercise)
            }
        }
    }
}