package ru.petprojects69.fitgram.ui.initData

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import org.koin.android.ext.android.inject
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentInitialDataBinding
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.ui.MainActivity
import ru.petprojects69.fitgram.ui.MainActivity.Companion.PREF_USER_DOCUMENT_ID_KEY
import ru.petprojects69.fitgram.ui.MainActivity.Companion.PREF_USER_ID_KEY
import ru.petprojects69.fitgram.ui.initData.bottomSheetSex.BottomSheetSexFragment
import ru.petprojects69.fitgram.ui.initData.bottomSheetSex.SexCallback
import ru.petprojects69.fitgram.ui.initData.bottomSheetTarget.BottomSheetTargetFragment
import ru.petprojects69.fitgram.ui.initData.bottomSheetTarget.TargetCallback
import ru.petprojects69.fitgram.ui.userProfileFragment.UserSex
import ru.petprojects69.fitgram.ui.userProfileFragment.UserTarget
import ru.petprojects69.fitgram.ui.utils.*

class InitialDataFragment : Fragment(R.layout.fragment_initial_data) {
    private var userId: String = ""
    private val binding: FragmentInitialDataBinding by viewBinding()
    private val preferences: SharedPreferences by inject()
    private val editor: SharedPreferences.Editor by inject()
    private val viewModel: InitDataViewModel by viewModels()
    private val controller by lazy { activity as MainActivityController }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = preferences.getString(PREF_USER_ID_KEY, null).toString()
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.getUserData(userId)

        val targetCallback: TargetCallback = object : TargetCallback {
            override fun weightLoss() {
                binding.targetInfoTextView.text = UserTarget.WEIGHT_LOSS.target
            }

            override fun keepingInShape() {
                binding.targetInfoTextView.text = UserTarget.KEEPING_IN_SHAPE.target
            }

            override fun massSet() {
                binding.targetInfoTextView.text = UserTarget.MASS_SET.target
            }

            override fun notDefined() {
                binding.targetInfoTextView.text = UserTarget.NOT_DEFINED.target
            }
        }

        val sexCallback: SexCallback = object : SexCallback {
            override fun setMan() {
                binding.sexInfoTextView.text = UserSex.MAN.sex
                binding.avatarImageView.load(R.drawable.man_placeholder) {
                    transformations(CircleCropTransformation())
                }
            }

            override fun setWoman() {
                binding.sexInfoTextView.text = UserSex.WOMAN.sex
                binding.avatarImageView.load(R.drawable.woman_placeholder) {
                    transformations(CircleCropTransformation())
                }
            }

            override fun setNotDefined() {
                binding.sexInfoTextView.text = UserSex.NOT_DEFINED.sex
                binding.avatarImageView.load(R.drawable.not_defined_placeholder) {
                    transformations(CircleCropTransformation())
                }
            }

        }
        binding.inputTargetLayout.setOnClickListener {
            BottomSheetTargetFragment(targetCallback, getUserTarget()).show(
                childFragmentManager,
                null
            )
        }

        binding.sexInputLayout.setOnClickListener {
            BottomSheetSexFragment(
                sex = getUserSex(),
                callback = sexCallback
            ).show(childFragmentManager, null)
        }

        binding.inputNameEditText
            .customBehaviorHintAndCursor(resources.getString(R.string.hint_name))

        binding.inputSurnameEditText
            .customBehaviorHintAndCursor(resources.getString(R.string.hint_surname))

        binding.inputAgeEditText
            .customBehaviorHintAndCursor(resources.getString(R.string.hint_age))

        binding.inputHeightEditText
            .customBehaviorHintAndCursor(resources.getString(R.string.hint_height))

        binding.inputWeightEditText.apply {
            customBehaviorHintAndCursor(resources.getString(R.string.hint_weight))
            setDecimalLimit()
        }

        binding.saveDataButton.setOnClickListener {
            editor.putBoolean(MainActivity.PREF_IS_FILLED_USER_DATA, true).commit()
            val user = UserEntity(
                id = userId,
                name = getUserName(),
                surname = getUserSurname(),
                age = getUserAge(),
                sex = getUserSex(),
                height = getUserHeight(),
                weight = getUserWeight(),
                target = getUserTarget()
            )
            viewModel.saveUserData(user)
        }
    }

    private fun getUserSex(): String = binding.sexInfoTextView.text.toString()

    private fun getUserTarget() =
        if (binding.targetInfoTextView.text.isNullOrBlank()) {
            UserTarget.NOT_DEFINED.target
        } else {
            binding.targetInfoTextView.text.toString()
        }

    private fun getUserWeight() = binding.inputWeightEditText.text.toString().toFloatOrNull()

    private fun getUserHeight() = binding.inputHeightEditText.text.toString().toIntOrNull()

    private fun getUserAge() = binding.inputAgeEditText.text.toString().toIntOrNull()

    private fun getUserName() =
        if (binding.inputNameEditText.text.isNullOrBlank()) {
            null
        } else {
            binding.inputNameEditText.text.toString()
        }

    private fun getUserSurname() =
        if (binding.inputSurnameEditText.text.isNullOrBlank()) {
            null
        } else {
            binding.inputSurnameEditText.text.toString()
        }

    private fun renderData(state: SaveUserDataState) {
        when (state) {
            is SaveUserDataState.Loading -> {
                binding.progressLayout.visibility = View.VISIBLE
            }
            is SaveUserDataState.Success -> {
                editor.putString(PREF_USER_DOCUMENT_ID_KEY, state.documentId)
                binding.progressLayout.visibility = View.GONE
                binding.root.showSnack("Данные сохранены")
                controller.startMainFragment()
            }
            is SaveUserDataState.Error -> {
                binding.progressLayout.visibility = View.GONE
                binding.root.showSnack(state.e.message.toString())
            }
            is SaveUserDataState.DataReceived -> {
                binding.progressLayout.visibility = View.GONE

                binding.avatarImageView.setAvatar(state.userEntity?.sex)

                binding.sexInfoTextView.text = state.userEntity?.sex

                state.userEntity?.name?.let {
                    binding.inputNameEditText.apply {
                        gravity = Gravity.END
                        text = it.toEditable()
                    }
                }
                state.userEntity?.surname?.let {
                    binding.inputSurnameEditText.apply {
                        gravity = Gravity.END
                        text = it.toEditable()
                    }
                }

                state.userEntity?.height?.let {
                    binding.inputHeightEditText.apply {
                        gravity = Gravity.END
                        text = it.toString().toEditable()
                    }
                }
                state.userEntity?.weight?.let {
                    binding.inputWeightEditText.apply {
                        gravity = Gravity.END
                        text = it.toString().toEditable()
                    }
                }
                state.userEntity?.age?.let {
                    binding.inputAgeEditText.apply {
                        gravity = Gravity.END
                        text = it.toString().toEditable()
                    }
                }
                state.userEntity?.target?.let {
                    binding.targetInfoTextView.text = it
                }
            }

            is SaveUserDataState.EmptyData -> {
                binding.progressLayout.visibility = View.GONE
            }
        }
    }
}