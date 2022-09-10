package ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity

class DetailsExerciseDialogFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getAerobicExerciseForId(idExercise: Int): LiveData<AerobicExerciseEntity> =
        viewModelScope.async {
            repository.getAerobicExForId(idExercise).asLiveData()
        }.getCompleted()
}