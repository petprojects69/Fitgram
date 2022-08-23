package ru.petprojects69.fitgram.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.exercise.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.ExerciseList
import ru.petprojects69.fitgram.domain.entity.exercise.PowerExerciseEntity

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM power_exercise_table")
    fun getAllPowerExercises(): Flow<MutableList<PowerExerciseEntity>>

    @Query("SELECT * FROM aerobic_exercise_table")
    fun getAllAerobicExercises(): Flow<MutableList<AerobicExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPowerExercise(powerExercise: PowerExerciseEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAerobicExercise(aerobicExercise: AerobicExerciseEntity)

    @Query("DELETE FROM power_exercise_table")
    suspend fun deleteAllPowerExercises()

    @Query("DELETE FROM aerobic_exercise_table")
    suspend fun deleteAllAerobicExercises()
}