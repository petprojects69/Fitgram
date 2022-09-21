package ru.petprojects69.fitgram.ui.initData.bottomSheetSex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.petprojects69.fitgram.ui.userProfileFragment.UserSex

class BottomSheetSexViewModel : ViewModel() {
    private var liveData: MutableLiveData<UserSex> = MutableLiveData()

    fun getData(): LiveData<UserSex> = liveData

    fun setSex(userSex: String) {
        when (userSex) {
            UserSex.MAN.sex -> {
                liveData.postValue(UserSex.MAN)
            }

            UserSex.WOMAN.sex -> {
                liveData.postValue(UserSex.WOMAN)
            }

            UserSex.NOT_DEFINED.sex -> {
                liveData.postValue(UserSex.NOT_DEFINED)
            }
        }
    }
}