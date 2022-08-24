package ru.petprojects69.fitgram.ui.timeTableFragment

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class TimeTableViewModel(private val repository: ExerciseRepositoryImpl) : ViewModel() {

    val allPowerExercise: LiveData<MutableList<Training>> =
        repository.allPowerExercise.asLiveData()

    fun insertPowerExercise(powerExercise: Training) = viewModelScope.launch {
        repository.insertPowerExercise(powerExercise)
    }

    //Потом убирается в DI
    class TimeTableViewModelFactory(private val repository: ExerciseRepositoryImpl) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TimeTableViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return TimeTableViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}