package ru.petprojects69.fitgram.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM training")
    fun getAllTraining(): Flow<MutableList<TrainingEntity>>

    @Query("SELECT * FROM  exercise_table ")
    fun getAllEx(): Flow<MutableList<ExerciseEntity>>

    @Query("SELECT*FROM exercise_table WHERE type='AEROBIC'")
    fun getAllAerobicEx(): Flow<MutableList<ExerciseEntity>>

    @Query("SELECT*FROM exercise_table WHERE type='POWER'")
    fun getAllPowerEx(): Flow<MutableList<ExerciseEntity>>

    @Query("SELECT * FROM exercise_table WHERE exercise_id = :id")
    fun getExerciseForId(id: Int): Flow<ExerciseEntity>

    @Query("SELECT * FROM exercise_table WHERE exercise_name LIKE :exerciseName")
    fun findExercise(exerciseName: String): Flow<MutableList<ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTraining(training: TrainingEntity)

    @Insert(onConflict = REPLACE)
    suspend fun presetTraining(data: List<TrainingEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEx(exercise: ExerciseEntity)

    @Insert(onConflict = REPLACE)
    suspend fun presetEx(data: List<ExerciseEntity>)

    @Query("DELETE FROM training")
    suspend fun deleteAllPowerExercises()

    @Query("DELETE FROM exercise_table WHERE exercise_id = :id")
    suspend fun removeExerciseForId(id: Int)
}