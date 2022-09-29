package ru.petprojects69.fitgram.data.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.DatedTrainingEntity
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class ExerciseRepositoryImpl(private val appDatabaseDao: AppDatabaseDao) : ExerciseRepository {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertTraining(training: TrainingEntity) {
        appDatabaseDao.insertTraining(training)
    }

    override suspend fun getAllTraining(): Flow<MutableList<TrainingEntity>> =
        appDatabaseDao.getAllTraining()

    override suspend fun removeTrainingForId(id: Int) {
        appDatabaseDao.removeTrainingForId(id)
    }

    override suspend fun insertDatedTraining(training: DatedTrainingEntity) {
        appDatabaseDao.insertDatedTraining(training)
    }

    override suspend fun getDatedTraining(): Flow<MutableList<DatedTrainingEntity>> =
        appDatabaseDao.getDatedTraining()

    override suspend fun removeDatedTrainingForId(id: Int) {
        appDatabaseDao.removeDatedTrainingForId(id)
    }

    override suspend fun getAllEx(): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.getAllEx()

    override suspend fun getAllAerobicEx(): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.getAllAerobicEx()

    override suspend fun getAllPowerEx(): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.getAllPowerEx()

    override suspend fun getExerciseForId(idExercise: Int): Flow<ExerciseEntity> =
        appDatabaseDao.getExerciseForId(idExercise)

    override suspend fun removeExerciseForId(idExercise: Int) {
        appDatabaseDao.removeExerciseForId(idExercise)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insertEx(ex: ExerciseEntity) {
        appDatabaseDao.insertEx(ex)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun updateEx(
        id: Int, name: String, description: String, type: ExerciseType, posterCustom: String?,
    ) {
        appDatabaseDao.updateEx(
            id = id,
            name = name,
            description = description,
            type = type,
            posterCustom = posterCustom
        )
    }

    override suspend fun findExercise(exerciseName: String): Flow<MutableList<ExerciseEntity>> =
        appDatabaseDao.findExercise(exerciseName)
}