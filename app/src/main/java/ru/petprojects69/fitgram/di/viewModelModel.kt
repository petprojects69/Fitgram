package ru.petprojects69.fitgram.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment.ExerciseChooserViewModel
import ru.petprojects69.fitgram.ui.exerciseContructorDialogFragment.ExerciseConstructorDialogFragmentViewModel
import ru.petprojects69.fitgram.ui.exercisesFragment.aerobic.AerobicExercisesFragmentViewModel
import ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment.DetailsExerciseDialogFragmentViewModel
import ru.petprojects69.fitgram.ui.exercisesFragment.power.PowerExercisesFragmentViewModel
import ru.petprojects69.fitgram.ui.initData.bottomSheetTarget.BottomSheetTargetViewModel
import ru.petprojects69.fitgram.ui.initData.InitDataViewModel
import ru.petprojects69.fitgram.ui.initData.bottomSheetSex.BottomSheetSexViewModel
import ru.petprojects69.fitgram.ui.timeTableFragment.TimeTableViewModel
import ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment.TrainingConstructorViewModel
import ru.petprojects69.fitgram.ui.trainingsFragment.TrainingsViewModel

val viewModelModule = module {
    viewModel { TimeTableViewModel(repository = get()) }
    viewModel { TrainingsViewModel(repository = get()) }
    viewModel { AerobicExercisesFragmentViewModel(repository = get()) }
    viewModel { PowerExercisesFragmentViewModel(repository = get()) }

    viewModel { InitDataViewModel() }
    viewModel { BottomSheetTargetViewModel() }
    viewModel { BottomSheetSexViewModel() }

    viewModel { DetailsExerciseDialogFragmentViewModel(repository = get()) }
    viewModel { TrainingConstructorViewModel(repository = get()) }
    viewModel { ExerciseChooserViewModel(repository = get()) }
    viewModel { ExerciseConstructorDialogFragmentViewModel(repository = get()) }
}