package ru.petprojects69.fitgram.ui.userProfileFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.petprojects69.fitgram.domain.useCase.SearchUserDataInCloudUseCase
import ru.petprojects69.fitgram.domain.useCase.SignOutUseCase

class UserProfileViewModel : ViewModel(), KoinComponent {
    private val searchUserDataInCloudUseCase: SearchUserDataInCloudUseCase by inject()
    private val signOutUseCase: SignOutUseCase by inject()
    private var liveData: MutableLiveData<UserProfileState> = MutableLiveData()

    fun getData(userId: String?): LiveData<UserProfileState> {
        liveData.postValue(UserProfileState.Loading)
        viewModelScope.launch {
            kotlin.runCatching {
                searchUserDataInCloudUseCase.execute(userId)
            }
                .onSuccess { user ->
                    if (user != null) {
                        liveData.postValue(UserProfileState.Success(user))
                    } else {
                        liveData.postValue(UserProfileState.NotUser)
                    }
                }
                .onFailure { e ->
                    liveData.postValue(UserProfileState.Error(e))
                }
        }
        return liveData
    }

    fun signOut() {
        liveData.postValue(UserProfileState.Loading)
        val auth = Firebase.auth
        viewModelScope.launch {
            kotlin.runCatching {
                signOutUseCase.execute(auth)
            }
                .onSuccess {
                    liveData.postValue(UserProfileState.SignOut)
                }
                .onFailure { e ->
                    liveData.postValue(UserProfileState.Error(e))
                }
        }
    }
}