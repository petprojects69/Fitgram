package ru.petprojects69.fitgram.ui.exerciseContructorDialogFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class ExerciseConstructorDialogFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    suspend fun saveExercise(exercise: ExerciseEntity) {
        repository.insertEx(exercise)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getExerciseForId(idExercise: Int): LiveData<ExerciseEntity> =
        viewModelScope.async {
            repository.getExerciseForId(idExercise).asLiveData()
        }.getCompleted()
}
