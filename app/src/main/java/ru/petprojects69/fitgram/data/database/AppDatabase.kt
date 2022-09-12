package ru.petprojects69.fitgram.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

@Database(
    entities = [
        TrainingEntity::class,
        UserEntity::class,
        ExerciseEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDatabaseDao(): AppDatabaseDao
}

