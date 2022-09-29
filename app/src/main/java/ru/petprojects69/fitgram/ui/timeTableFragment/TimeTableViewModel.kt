package ru.petprojects69.fitgram.ui.timeTableFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.useCase.GetTrainingInLocalStorageUseCase

class TimeTableViewModel : ViewModel(), KoinComponent {
    private val getTrainingInLocalStorageUseCase: GetTrainingInLocalStorageUseCase by inject()

    fun getAllTrainings(): LiveData<MutableList<TrainingEntity>> =
        getTrainingInLocalStorageUseCase.allTrainings(viewModelScope)
        
        fun removeDatedTraining(id: Int) {
        viewModelScope.launch { repository.removeDatedTrainingForId(id) }
    }
}