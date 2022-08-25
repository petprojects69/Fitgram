package ru.petprojects69.fitgram.ui.exercisesFragment.power

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class PowerExerciseFragmentViewModel(private val repository: ExerciseRepositoryImpl) : ViewModel() {

    val allPowerExercise: LiveData<MutableList<PowerExerciseEntity>> =
        repository.allPowerEx.asLiveData()

    fun insertPowerExercise(powerExercise: PowerExerciseEntity) = viewModelScope.launch {
        repository.insertPowerEx(powerExercise)
    }

    class ExerciseFragmentViewModelFactory(private val repository: ExerciseRepositoryImpl) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PowerExerciseFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PowerExerciseFragmentViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}