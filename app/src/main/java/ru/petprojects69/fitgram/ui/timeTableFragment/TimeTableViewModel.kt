package ru.petprojects69.fitgram.ui.timeTableFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.domain.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

class TimeTableViewModel(private val repository: ExerciseRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allTrainings: LiveData<MutableList<TrainingEntity>> = viewModelScope.async {
        repository.getAllTraining().asLiveData()
    }.getCompleted()

}