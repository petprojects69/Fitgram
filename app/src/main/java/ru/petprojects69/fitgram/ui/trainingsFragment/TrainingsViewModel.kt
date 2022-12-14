package ru.petprojects69.fitgram.ui.trainingsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

class TrainingsViewModel(private val repository: ExerciseRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val allTrainings: LiveData<MutableList<TrainingEntity>> = viewModelScope.async {
        repository.getAllTraining().asLiveData()
    }.getCompleted()

    fun removeTraining(id: String) {
        viewModelScope.launch {
            repository.removeTrainingForId(id)
        }
    }

}