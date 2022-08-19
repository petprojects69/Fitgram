package ru.petprojects69.fitgram.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.model.database.entity.PowerExerciseEntity

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM power_exercise_table")
    fun getAllPowerExercises(): Flow<List<PowerExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPowerExercise(powerExercise: PowerExerciseEntity)

    @Query("DELETE FROM power_exercise_table")
    suspend fun deleteAllPowerExercises()
}