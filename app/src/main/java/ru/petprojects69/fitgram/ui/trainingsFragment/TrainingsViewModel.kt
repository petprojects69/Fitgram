package ru.petprojects69.fitgram.ui.trainingsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.model.database.ExerciseRepositoryImpl

class TrainingsViewModel(private val repository: ExerciseRepositoryImpl) : ViewModel() {
    val allTrainings: LiveData<MutableList<Training>> =
        repository.allTraining.asLiveData()
}