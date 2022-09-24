package ru.petprojects69.fitgram.ui.initData.bottomSheetTarget

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.petprojects69.fitgram.ui.userProfileFragment.UserTarget

class BottomSheetTargetViewModel : ViewModel() {
    private val liveData: MutableLiveData<UserTarget> = MutableLiveData()
    fun getData(): LiveData<UserTarget> = liveData

    fun setTarget(target: String) {
        when (target) {
            UserTarget.WEIGHT_LOSS.target -> {
                liveData.postValue(UserTarget.WEIGHT_LOSS)
            }
            UserTarget.KEEPING_IN_SHAPE.target -> {
                liveData.postValue(UserTarget.KEEPING_IN_SHAPE)
            }
            UserTarget.MASS_SET.target -> {
                liveData.postValue(UserTarget.MASS_SET)
            }
            UserTarget.NOT_DEFINED.target -> {
                liveData.postValue(UserTarget.NOT_DEFINED)
            }
        }
    }
}