package ru.petprojects69.fitgram.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

@Database(
    entities = [
        Training::class,
        UserEntity::class,
        AerobicExerciseEntity::class,
        PowerExerciseEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDatabaseDao(): AppDatabaseDao
}

