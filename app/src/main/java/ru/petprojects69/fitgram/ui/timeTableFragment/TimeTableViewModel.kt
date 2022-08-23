package ru.petprojects69.fitgram.ui.timeTableFragment

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.exercise.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.ExerciseList
import ru.petprojects69.fitgram.domain.entity.exercise.PowerExerciseEntity
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class TimeTableViewModel(private val repository: ExerciseRepositoryImpl) : ViewModel() {

    val allPowerExercise: LiveData<MutableList<PowerExerciseEntity>> =
        repository.allPowerExercise.asLiveData()

    val allAerobicExercise: LiveData<MutableList<AerobicExerciseEntity>> =
        repository.allAerobicExercise.asLiveData()

    val allExercise: LiveData<MutableList<ExerciseList>> =
        repository.allExercise.asLiveData()

    fun insertPowerExercise(powerExercise: PowerExerciseEntity) = viewModelScope.launch {
        repository.insertPowerExercise(powerExercise)
    }

    fun insertAerobicExercise(aerobicExercise: AerobicExerciseEntity) = viewModelScope.launch {
        repository.insertAerobicExercise(aerobicExercise)
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
