package ru.petprojects69.fitgram.ui.exercisesFragment.power

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class PowerExercisesFragmentViewModel(private val repository: ExerciseRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allPowerExercise: LiveData<MutableList<ExerciseEntity>> = viewModelScope.async {
        repository.getAllPowerEx().asLiveData()
    }.getCompleted()

    fun insertExercise(exercise: ExerciseEntity) = viewModelScope.launch {
        repository.insertEx(exercise)
    }

}