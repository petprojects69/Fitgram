package ru.petprojects69.fitgram.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.DatedTrainingEntity
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM training")
    fun getAllTraining(): Flow<MutableList<TrainingEntity>>

    @Query("SELECT*FROM dated_training ORDER BY date")
    fun getDatedTraining():Flow<MutableList<DatedTrainingEntity>>

    @Query("SELECT * FROM  exercise_table ")
    fun getAllEx(): Flow<MutableList<ExerciseEntity>>

    @Query("SELECT*FROM exercise_table WHERE type='AEROBIC'")
    fun getAllAerobicEx(): Flow<MutableList<ExerciseEntity>>

    @Query("SELECT*FROM exercise_table WHERE type='POWER'")
    fun getAllPowerEx(): Flow<MutableList<ExerciseEntity>>

    @Query("SELECT * FROM exercise_table WHERE exercise_id = :id")
    fun getExerciseForId(id: String): Flow<ExerciseEntity>

    @Query("SELECT * FROM exercise_table WHERE exercise_name LIKE :exerciseName")
    fun findExercise(exerciseName: String): Flow<MutableList<ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDatedTraining(training: DatedTrainingEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTraining(training: TrainingEntity)

    @Insert(onConflict = REPLACE)
    suspend fun presetTraining(data: List<TrainingEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEx(exercise: ExerciseEntity)

    @Query("UPDATE exercise_table SET " +
            "exercise_name =:name, " +
            "type = :type, " +
            "exercise_description = :description," +
            "exercise_posterCustom = :posterCustom " +
            "WHERE exercise_id = :id")
    suspend fun updateEx(
        id: Int,
        name: String,
        type: ExerciseType,
        description: String,
        posterCustom: String?,
    )

    @Insert(onConflict = REPLACE)
    suspend fun presetEx(data: List<ExerciseEntity>)

    @Query("DELETE FROM training")
    suspend fun deleteAllPowerExercises()

    @Query("DELETE FROM exercise_table WHERE exercise_id = :id")
    suspend fun removeExerciseForId(id: Int)

    @Query("DELETE FROM dated_training WHERE datedTrainingId = :id")
    suspend fun removeDatedTrainingForId(id: Int)

    @Query("DELETE FROM training WHERE id = :id")
    suspend fun removeTrainingForId(id: Int)
}