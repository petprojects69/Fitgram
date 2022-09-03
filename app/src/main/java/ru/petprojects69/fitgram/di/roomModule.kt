package ru.petprojects69.fitgram.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.petprojects69.fitgram.data.database.AppDatabase

private const val DB_NAME = "FitgramDatabase"

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    single { get<AppDatabase>().appDatabaseDao() }
}