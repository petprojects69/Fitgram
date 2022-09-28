package ru.petprojects69.fitgram.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import ru.petprojects69.fitgram.data.database.AppDatabaseDao
import ru.petprojects69.fitgram.di.PRESET_EXERCISE
import ru.petprojects69.fitgram.di.PRESET_TRAINING
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.useCase.GetTrainingInLocalStorageUseCase
import ru.petprojects69.fitgram.domain.useCase.SaveTrainingDataInRemoteUseCase
import ru.petprojects69.fitgram.ui.utils.awaitTask

class MainViewModel : ViewModel(), KoinComponent {
    private val dao: AppDatabaseDao by inject()
    private val saveTrainingDataInRemoteUseCase: SaveTrainingDataInRemoteUseCase by inject()
    private val getTrainingInLocalStorageUseCase: GetTrainingInLocalStorageUseCase by inject()
    private val trainingData: List<TrainingEntity> by inject(named(PRESET_TRAINING))
    private val powerExercise: List<ExerciseEntity> by inject(named(PRESET_EXERCISE))

    fun getTrainingInLocal(): LiveData<MutableList<TrainingEntity>> {
        return getTrainingInLocalStorageUseCase.allTrainings(viewModelScope)
    }

    fun presetDataInLocal() {
        viewModelScope.launch {
            kotlin.runCatching {
                presetInLocalStorage()
            }
                .onSuccess {
                    Log.i(MainActivity.TAG, "Data preset in local SUCCESS")
                }
                .onFailure {
                    Log.i(MainActivity.TAG, "Data preset in local FAILURE: ${it.message}")
                }
        }
    }

    private suspend fun presetInLocalStorage() {
        dao.presetTraining(trainingData)
        dao.presetEx(powerExercise)
    }

    fun presetInRemoteStorage(trainingList: MutableList<TrainingEntity>?) {
        val listTask = saveTrainingDataInRemoteUseCase.execute(trainingList)
        listTask.forEach { task ->
            viewModelScope.launch {
                kotlin.runCatching {
                    awaitTask(task)
                }
                    .onSuccess {
                        Log.i(MainActivity.TAG, "Data preset in local SUCCESS")
                    }
                    .onFailure {
                        Log.i(MainActivity.TAG, "Data preset in local FAILURE: ${it.message}")
                    }
            }
        }
    }
}