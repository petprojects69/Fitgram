package ru.petprojects69.fitgram.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import ru.petprojects69.fitgram.domain.useCase.SaveUserDataInCloudUseCase
import ru.petprojects69.fitgram.domain.useCase.SearchUserDataInCloudUseCase
import ru.petprojects69.fitgram.domain.useCase.SignOutUseCase

val useCase = module {
    single { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
    single { SaveUserDataInCloudUseCase(cloudDb = get(), coroutineScope = get()) }
    single { SearchUserDataInCloudUseCase(cloudDb = get(), coroutineScope = get()) }
    single { SignOutUseCase() }
}