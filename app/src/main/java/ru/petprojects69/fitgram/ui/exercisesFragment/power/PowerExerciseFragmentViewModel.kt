package ru.petprojects69.fitgram.ui.exercisesFragment.power

import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.data.database.ExerciseRepositoryImpl
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

class PowerExerciseFragmentViewModel(private val repository: ExerciseRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allPowerExercise: LiveData<MutableList<PowerExerciseEntity>> = viewModelScope.async {
        repository.getAllPowerEx().asLiveData()
    }.getCompleted()

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