package ru.petprojects69.fitgram.di

import org.koin.dsl.module
import ru.petprojects69.fitgram.data.database.ExerciseRepositoryImpl
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

val repositoryModule = module {
    single<ExerciseRepository> { ExerciseRepositoryImpl(appDatabaseDao = get()) }
}