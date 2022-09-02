package ru.petprojects69.fitgram.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM training")
    fun getAllTraining(): Flow<MutableList<TrainingEntity>>

    @Query("SELECT * FROM power_exercise_table")
    fun getAllPowerEx(): Flow<MutableList<PowerExerciseEntity>>

    @Query("SELECT * FROM aerobic_exercise_table")
    fun getAllAerobicEx(): Flow<MutableList<AerobicExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTraining(powerExercise: TrainingEntity)

    @Insert(onConflict = REPLACE)
    suspend fun presetTraining(data: List<TrainingEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPowerEx(powerExercise: PowerExerciseEntity)

    @Insert(onConflict = REPLACE)
    suspend fun presetPowerEx(data: List<PowerExerciseEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAerobicEx(aerobicExercise: AerobicExerciseEntity)

    @Insert(onConflict = REPLACE)
    suspend fun presetAerobicEx(data: List<AerobicExerciseEntity>)

    @Query("DELETE FROM training")
    suspend fun deleteAllPowerExercises()
}