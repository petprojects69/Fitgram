package ru.petprojects69.fitgram

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.petprojects69.fitgram.di.dataPresetModule
import ru.petprojects69.fitgram.di.repositoryModule
import ru.petprojects69.fitgram.di.roomModule
import ru.petprojects69.fitgram.di.viewModelModule

class AppFitgram : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppFitgram)
            modules(
                listOf(
                    viewModelModule,
                    roomModule,
                    repositoryModule,
                    dataPresetModule
                )
            )
        }
    }
}