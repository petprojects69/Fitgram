package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class ExerciseChooserViewModel(private val repository: ExerciseRepository) : ViewModel() {

    val allExercises = viewModelScope.async {
        repository.getAllEx().asLiveData()
    }.getCompleted()

    fun searchExercise(exerciseName: String): LiveData<MutableList<ExerciseEntity>> =
        viewModelScope.async {
            repository.findExercise(exerciseName).asLiveData()
        }.getCompleted()
}