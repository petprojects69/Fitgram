package ru.petprojects69.fitgram.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.petprojects69.fitgram.domain.entity.Training

@Dao
interface AppDatabaseDao {

    @Query("SELECT * FROM training")
    fun getAllTraining(): Flow<MutableList<Training>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTraining(powerExercise: Training)

    @Insert(onConflict = REPLACE)
    suspend fun presetTraining(data: List<Training>)

    @Query("DELETE FROM training")
    suspend fun deleteAllPowerExercises()
}