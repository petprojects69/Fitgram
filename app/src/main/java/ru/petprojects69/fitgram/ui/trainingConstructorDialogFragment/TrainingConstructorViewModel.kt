package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import androidx.lifecycle.ViewModel
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.usecase.ExerciseRepository

class TrainingConstructorViewModel(private val repository: ExerciseRepository) : ViewModel() {
    suspend fun saveTraining(training: TrainingEntity) {
        repository.insertTraining(training)
    }
}