package ru.petprojects69.fitgram.ui.exercisesFragment.power

import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.data.database.ExerciseRepositoryImpl
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

    class ExerciseFragmentViewModelFactory(private val repository: ExerciseRepositoryImpl) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PowerExercisesFragmentViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PowerExercisesFragmentViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}