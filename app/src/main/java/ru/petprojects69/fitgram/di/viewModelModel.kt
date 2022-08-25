package ru.petprojects69.fitgram.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.petprojects69.fitgram.ui.exercisesFragment.aerobic.AerobicExerciseFragmentViewModel
import ru.petprojects69.fitgram.ui.exercisesFragment.power.PowerExerciseFragmentViewModel
import ru.petprojects69.fitgram.ui.timeTableFragment.TimeTableViewModel
import ru.petprojects69.fitgram.ui.trainingsFragment.TrainingsViewModel

val viewModelModule = module {
    viewModel { TimeTableViewModel(repository = get()) }
    viewModel { TrainingsViewModel(repository = get()) }
    viewModel { AerobicExerciseFragmentViewModel(repository = get()) }
    viewModel { PowerExerciseFragmentViewModel(repository = get()) }
}