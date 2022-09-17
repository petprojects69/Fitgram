package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class AerobicExercisesFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allAerobicExercise: LiveData<MutableList<ExerciseEntity>> = viewModelScope.async {
        repository.getAllAerobicEx().asLiveData()
    }.getCompleted()

    fun insertExercise(exercise: ExerciseEntity) = viewModelScope.launch {
        repository.insertEx(exercise)
    }
}