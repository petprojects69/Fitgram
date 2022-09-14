package ru.petprojects69.fitgram.ui.initData

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.domain.useCase.SaveUserDataInCloudUseCase
import ru.petprojects69.fitgram.domain.useCase.SearchUserDataUseCase

class InitDataViewModel : ViewModel(), KoinComponent {
    private val cloudDb: FirebaseFirestore by inject()
    private val liveData: MutableLiveData<SaveUserDataState> = MutableLiveData()

    private val useCaseSaveUserData = SaveUserDataInCloudUseCase(cloudDb, viewModelScope)
    private val useCaseSearchUserData = SearchUserDataUseCase(cloudDb, viewModelScope)

    fun getLiveData(): LiveData<SaveUserDataState> {
        return liveData
    }

    fun getUserData(userId: String) {
        liveData.postValue(SaveUserDataState.Loading)
        viewModelScope.launch {
            kotlin.runCatching {
                useCaseSearchUserData.execute(userId)
            }
                .onSuccess { id ->
                    if (id != null) {
                        liveData.postValue(SaveUserDataState.DataReceived(id))
                    } else {
                        liveData.postValue(SaveUserDataState.EmptyData)
                    }
                }
                .onFailure {
                    liveData.postValue(SaveUserDataState.Error(it))
                }
        }
    }

    fun saveUserData(user: UserEntity) {
        liveData.postValue(SaveUserDataState.Loading)
        viewModelScope.launch {
            liveData.postValue(useCaseSaveUserData.execute(user))
        }
    }
}