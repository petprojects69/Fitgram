package ru.petprojects69.fitgram.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.petprojects69.fitgram.domain.preset.presetAerobicExercise
import ru.petprojects69.fitgram.domain.preset.presetPowerExercise
import ru.petprojects69.fitgram.domain.preset.presetTrainings

const val PRESET_TRAINING = "presetTraining"
const val PRESET_AEROBIC = "presetAerobic"
const val PRESET_POWER = "presetPower"
val dataPresetModule = module {
    single(named(PRESET_TRAINING)) { presetTrainings }
    single(named(PRESET_AEROBIC)) { presetAerobicExercise }
    single(named(PRESET_POWER)) { presetPowerExercise }
}