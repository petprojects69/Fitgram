package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class AerobicExerciseFragmentViewModel(private val repository: ExerciseRepositoryImpl) :
    ViewModel() {

    val allAerobicExercise: LiveData<MutableList<AerobicExerciseEntity>> =
        repository.allAerobicEx.asLiveData()

    fun insertAerobicExercise(aerobicExercise: AerobicExerciseEntity) = viewModelScope.launch {
        repository.insertAerobicEx(aerobicExercise)
    }
}