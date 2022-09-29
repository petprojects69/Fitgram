package ru.petprojects69.fitgram.domain.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import ru.petprojects69.fitgram.data.database.AppDatabaseDao
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

class GetTrainingInLocalStorageUseCase(private val dao: AppDatabaseDao) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun allTrainings(scope: CoroutineScope): LiveData<MutableList<TrainingEntity>> = scope.async {
        dao.getAllTraining().asLiveData()
    }.getCompleted()
}