package ru.petprojects69.fitgram.ui.timeTableFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class TimeTableViewModel(private val repository: ExerciseRepositoryImpl) : ViewModel() {

    val allPowerExercise: LiveData<MutableList<Training>> =
        repository.allPowerExercise.asLiveData()

    fun insertPowerExercise(powerExercise: Training) = viewModelScope.launch {
        repository.insertPowerExercise(powerExercise)
    }
}