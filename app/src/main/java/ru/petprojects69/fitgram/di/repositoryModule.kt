package ru.petprojects69.fitgram.di

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module
import ru.petprojects69.fitgram.data.database.ExerciseRepositoryImpl
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

val repositoryModule = module {
    single<ExerciseRepository> { ExerciseRepositoryImpl(appDatabaseDao = get()) }
    single { Firebase.firestore }
}