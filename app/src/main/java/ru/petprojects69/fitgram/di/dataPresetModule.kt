package ru.petprojects69.fitgram.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.petprojects69.fitgram.data.preset.power.presetExercises
import ru.petprojects69.fitgram.data.preset.training.presetTrainings

const val PRESET_TRAINING = "presetTraining"
const val PRESET_EXERCISE = "presetExercise"
val dataPresetModule = module {
    single(named(PRESET_TRAINING)) { presetTrainings }
    single(named(PRESET_EXERCISE)) { presetExercises }
}