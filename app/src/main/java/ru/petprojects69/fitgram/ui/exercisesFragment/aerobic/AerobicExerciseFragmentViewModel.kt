package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity

class AerobicExerciseFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allAerobicExercise: LiveData<MutableList<AerobicExerciseEntity>> = viewModelScope.async {
        repository.getAllAerobicEx().asLiveData()
    }.getCompleted()


    fun insertAerobicExercise(aerobicExercise: AerobicExerciseEntity) = viewModelScope.launch {
        repository.insertAerobicEx(aerobicExercise)
    }
}