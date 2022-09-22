package ru.petprojects69.fitgram.ui.exerciseContructorDialogFragment

import androidx.lifecycle.ViewModel
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class ExerciseConstructorDialogFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    suspend fun saveExercise(exercise: ExerciseEntity) {
        repository.insertEx(exercise)
    }
}
