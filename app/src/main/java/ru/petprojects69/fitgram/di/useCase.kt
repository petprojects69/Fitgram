package ru.petprojects69.fitgram.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import ru.petprojects69.fitgram.domain.useCase.*

val useCase = module {
    factory { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
    single { SaveUserDataInCloudUseCase(cloudDb = get(), coroutineScope = get()) }
    single { SearchUserDataInCloudUseCase(cloudDb = get(), coroutineScope = get()) }
    single { SaveTrainingDataInRemoteUseCase(cloudDb = get()) }
    single { GetTrainingInLocalStorageUseCase(dao = get()) }
    single { SignOutUseCase() }
}