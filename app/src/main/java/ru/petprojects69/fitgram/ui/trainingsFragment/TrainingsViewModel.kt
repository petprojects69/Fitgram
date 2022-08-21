package ru.petprojects69.fitgram.ui.trainingsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class TrainingsViewModel(private val repository: ExerciseRepositoryImpl) : ViewModel() {
    // TODO изменить ExerciseEntity на Тренировка
    val allTrainings: LiveData<MutableList<PowerExerciseEntity>> =
        repository.allPowerExercise.asLiveData()
}

//Потом убирается в DI
class TrainingsViewModelFactory(private val repository: ExerciseRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrainingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TrainingsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}