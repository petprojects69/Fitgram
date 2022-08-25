package ru.petprojects69.fitgram.di

import org.koin.dsl.module
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

val repositoryModule = module {
    single { ExerciseRepositoryImpl(appDatabaseDao = get()) }
}