package ru.petprojects69.fitgram.ui.userProfileFragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentUserProfileBinding
import ru.petprojects69.fitgram.domain.setCalorieNorm
import ru.petprojects69.fitgram.signIn.SignInFirebaseActivity
import ru.petprojects69.fitgram.ui.MainActivity
import ru.petprojects69.fitgram.ui.initData.MainActivityController
import ru.petprojects69.fitgram.ui.utils.setAvatar
import ru.petprojects69.fitgram.ui.utils.showSnack

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

    private val binding: FragmentUserProfileBinding by viewBinding()
    private val viewModel: UserProfileViewModel by viewModels()
    private val preferences: SharedPreferences by inject()
    private var userId: String? = null
    private val controller: MainActivityController by lazy { activity as MainActivityController }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = preferences.getString(MainActivity.PREF_USER_ID_KEY, null)
        viewModel.getData(userId).observe(viewLifecycleOwner) {
            renderData(it)
        }
        binding.gradeInfoImageView.setOnClickListener {
            showGradeInfo()
        }
        binding.logoutButton.setOnClickListener {
            changeUser()
        }
        binding.signInButton.setOnClickListener {
            changeUser()
        }
        binding.editButton.setOnClickListener {
            editUserData()
        }
    }

    private fun renderData(state: UserProfileState) {
        when (state) {
            is UserProfileState.Loading -> {
                binding.progressLayout.visibility = View.VISIBLE
            }
            is UserProfileState.Success -> {
                binding.progressLayout.visibility = View.GONE
                binding.apply {
                    binding.userImageView.setAvatar(state.user.sex)
                    state.user.name?.let { userNameTextView.text = it }
                    state.user.surname?.let { userSurNameTextView.text = it }

                    userGradeTextView.text = setUserRank(state).rank
                    userGenderTextView.text = state.user.sex

                    state.user.height?.let {
                        userHeightTextView.text =
                            resources.getString(R.string.user_height_text, it.toString())
                    }
                    state.user.weight?.let {
                        userWeightTextView.text =
                            resources.getString(R.string.user_weight_text, it.toString())
                    }
                    state.user.age?.let { userAgeTextView.text = it.toString() }
                    state.user.target?.let { userTarget.text = it }

                    userCaloriesTextView.text = setCalorieNorm(state.user)
                }
            }
            is UserProfileState.Error -> {
                binding.progressLayout.visibility = View.GONE
                binding.root.showSnack(state.e.message.toString())
            }
            is UserProfileState.SignOut -> {
                binding.progressLayout.visibility = View.GONE
                val intent = Intent(requireContext(), SignInFirebaseActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            is UserProfileState.NotUser -> {
                binding.progressLayout.visibility = View.GONE
                binding.infoLayout.visibility = View.GONE
                binding.notUserLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun changeUser() {
        viewModel.signOut()
    }

    private fun showGradeInfo() {
        Toast.makeText(
            context,
            R.string.grade_info,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun editUserData() {
        controller.startInitFragment()
    }
}