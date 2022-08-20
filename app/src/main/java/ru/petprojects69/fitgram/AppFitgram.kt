package ru.petprojects69.fitgram

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import ru.petprojects69.fitgram.model.database.AppDatabase
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class AppFitgram : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ExerciseRepositoryImpl(database.appDatabaseDao()) }
}