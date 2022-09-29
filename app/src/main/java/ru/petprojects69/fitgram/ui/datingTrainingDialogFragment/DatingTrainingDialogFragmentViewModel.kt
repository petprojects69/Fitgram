package ru.petprojects69.fitgram.ui.datingTrainingDialogFragment

import androidx.lifecycle.ViewModel
import ru.petprojects69.fitgram.domain.entity.DatedTrainingEntity
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class DatingTrainingDialogFragmentViewModel(private val repository: ExerciseRepository) :
    ViewModel() {

    suspend fun saveDatedTraining(training: DatedTrainingEntity) {
        repository.insertDatedTraining(training)
    }
}