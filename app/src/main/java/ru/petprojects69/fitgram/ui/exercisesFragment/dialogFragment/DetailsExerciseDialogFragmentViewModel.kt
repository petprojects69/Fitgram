package ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class DetailsExerciseDialogFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAerobicExerciseForId(idExercise: String): LiveData<ExerciseEntity> =
        viewModelScope.async {
            repository.getExerciseForId(idExercise).asLiveData()
        }.getCompleted()
}