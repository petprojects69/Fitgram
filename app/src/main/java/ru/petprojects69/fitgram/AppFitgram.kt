package ru.petprojects69.fitgram

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.petprojects69.fitgram.di.repositoryModule
import ru.petprojects69.fitgram.di.roomModule
import ru.petprojects69.fitgram.di.viewModelModule
import ru.petprojects69.fitgram.model.database.AppDatabase
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class AppFitgram : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppFitgram)
            modules(
                listOf(
                    viewModelModule,
                    roomModule,
                    repositoryModule
                )
            )
        }
    }

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ExerciseRepositoryImpl(database.appDatabaseDao()) }
}