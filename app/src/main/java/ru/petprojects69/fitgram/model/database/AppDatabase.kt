package ru.petprojects69.fitgram.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.domain.entity.exercise.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.PowerExerciseEntity

private const val DB_NAME = "FitgramDatabase"

@Database(entities = [
    UserEntity::class,
    AerobicExerciseEntity::class,
    PowerExerciseEntity::class],
    version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun appDatabaseDao(): AppDatabaseDao

    private class AppDatabaseCallback(
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.appDatabaseDao())
                }
            }
        }

        //Test
        suspend fun populateDatabase(appDatabaseDao: AppDatabaseDao) {
            appDatabaseDao.deleteAllPowerExercises()

            val testData1 = PowerExerciseEntity(
                exerciseBase = ExerciseEntity(name = "первое упражнение"),
                numberOfRepetitions = 14
            )

            val testData2 = PowerExerciseEntity(
                exerciseBase = ExerciseEntity(name = "Второе"),
                numberOfRepetitions = 25
            )
            val testData3 = AerobicExerciseEntity(
                exerciseBase = ExerciseEntity(name = "Aerobic"),
                leadTime = 25.7f
            )

            appDatabaseDao.insertPowerExercise(testData1)
            appDatabaseDao.insertPowerExercise(testData2)
            appDatabaseDao.insertAerobicExercise(testData3)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

