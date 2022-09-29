package ru.petprojects69.fitgram.ui.timeTableFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.domain.entity.DatedTrainingEntity
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class TimeTableViewModel(private val repository: ExerciseRepository) : ViewModel() {

    @OptIn(ExperimentalCoroutinesApi::class)
    val datedTrainings: LiveData<MutableList<DatedTrainingEntity>> = viewModelScope.async {
        repository.getDatedTraining().asLiveData()
    }.getCompleted()

}