package ru.petprojects69.fitgram.di

import org.koin.dsl.module
import ru.petprojects69.fitgram.domain.preset.presetTrainings

val dataPresetModule = module {
    single { presetTrainings }
}