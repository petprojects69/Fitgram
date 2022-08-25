package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl
import ru.petprojects69.fitgram.ui.exercisesFragment.power.PowerExerciseFragmentViewModel

class AerobicExerciseFragmentViewModel(private val repository: ExerciseRepositoryImpl) :
    ViewModel() {

    val allAerobicExercise: LiveData<MutableList<AerobicExerciseEntity>> =
        repository.allAerobicEx.asLiveData()

    fun insertAerobicExercise(aerobicExercise: AerobicExerciseEntity) = viewModelScope.launch {
        repository.insertAerobicEx(aerobicExercise)
    }

    class ExerciseFragmentViewModelFactory(private val repository: ExerciseRepositoryImpl) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AerobicExerciseFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AerobicExerciseFragmentViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}