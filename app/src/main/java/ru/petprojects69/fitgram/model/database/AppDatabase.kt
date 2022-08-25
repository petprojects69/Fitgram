package ru.petprojects69.fitgram.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

import ru.petprojects69.fitgram.domain.entity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.domain.entity.UserEntity

import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.*
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

private const val DB_NAME = "FitgramDatabase"

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

            val testData1 = Training(
                label = "Тренировка ОФП v.1",
                exerciseList = mutableListOf(
                    AerobicEx(labelAerobic = "Бег", type = AerobicType.RUN),
                    PowerEx(labelPower = "Жим"),
                    PowerEx(labelPower = "Тяга"),
                    PowerEx(labelPower = "Присед"),
                )
            )

            val testData2 = Training(
                label = "Тренировка ОФП v.2",
                exerciseList = mutableListOf(
                    AerobicEx(labelAerobic = "Плавание"),
                    PowerEx(labelPower = "Жим"),
                    PowerEx(labelPower = "Тяга", count = 5)
                )
            )


            val exTest1 = PowerExerciseEntity(
                exercise = ExerciseEntity(
                    name = "Отжимания"
                ),
                numberOfRepetitions = 25
            )

            val exTest2 = PowerExerciseEntity(
                exercise = ExerciseEntity(
                    name = "Приседания"
                ),
                numberOfRepetitions = 10
            )


            val exTest3 = AerobicExerciseEntity(
                exercise = ExerciseEntity(
                    name = "Бег трусцой"
                ),
                leadTime = 25f
            )

            val exTest4 = AerobicExerciseEntity(
                exercise = ExerciseEntity(
                    name = "Плавание"
                ),
                leadTime = 100f
            )

            appDatabaseDao.insertTraining(testData1)
            appDatabaseDao.insertTraining(testData2)

            appDatabaseDao.insertPowerEx(exTest1)
            appDatabaseDao.insertPowerEx(exTest2)

            appDatabaseDao.insertAerobicEx(exTest3)
            appDatabaseDao.insertAerobicEx(exTest4)
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

