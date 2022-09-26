package ru.petprojects69.fitgram.ui.detailExerciseDialogFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class DetailsExerciseDialogFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAerobicExerciseForId(idExercise: Int): LiveData<ExerciseEntity> =
        viewModelScope.async {
            repository.getExerciseForId(idExercise).asLiveData()
        }.getCompleted()

    fun removeExerciseForId(idExercise: Int) {
        viewModelScope.launch {
            repository.removeExerciseForId(idExercise)
        }
    }
}