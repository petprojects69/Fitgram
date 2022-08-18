package ru.petprojects69.fitgram.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.petprojects69.fitgram.model.database.entity.AerobicExerciseEntity
import ru.petprojects69.fitgram.model.database.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.model.database.entity.UserEntity

private const val DB_NAME = "FitgramDatabase"

@Database(entities = [
    UserEntity::class,
    AerobicExerciseEntity::class,
    PowerExerciseEntity::class],
    version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDatabaseDao(): AppDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}