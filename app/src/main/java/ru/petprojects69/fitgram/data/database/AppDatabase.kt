package ru.petprojects69.fitgram.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

@Database(
    entities = [
        TrainingEntity::class,
        UserEntity::class,
        AerobicExerciseEntity::class,
        PowerExerciseEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDatabaseDao(): AppDatabaseDao
}

