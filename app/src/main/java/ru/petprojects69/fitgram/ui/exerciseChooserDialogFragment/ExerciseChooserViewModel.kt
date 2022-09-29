package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class ExerciseChooserViewModel(private val repository: ExerciseRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allExercises = viewModelScope.async {
        repository.getAllEx().asLiveData()
    }.getCompleted()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun searchExercise(exerciseName: String): LiveData<MutableList<ExerciseEntity>> =
        viewModelScope.async {
            repository.findExercise(exerciseName).asLiveData()
        }.getCompleted()
}