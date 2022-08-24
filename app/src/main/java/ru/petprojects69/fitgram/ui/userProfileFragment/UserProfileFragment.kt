package ru.petprojects69.fitgram.ui.userProfileFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.data.FakeUser
import ru.petprojects69.fitgram.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {

    private val binding: FragmentUserProfileBinding by lazy {
        FragmentUserProfileBinding.bind(
            requireView()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fakeUser = FakeUser()
        val userNameSurname = listOf(fakeUser.name, fakeUser.surname)

        binding.apply {
            userNameTextView.text = userNameSurname.joinToString(separator = " ")
            userGradeTextView.append(fakeUser.rank)
            userGenderTextView.append(fakeUser.gender)
            userHeightTextView.append(fakeUser.height.toString())
            userWeightTextView.append(fakeUser.weight.toString())
            userAgeTextView.append(fakeUser.age.toString())
            userCaloriesTextView.append(fakeUser.calories.toString())
        }

        binding.gradeInfoImageView.setOnClickListener {
            showGradeInfo()
        }
    }

    private fun showGradeInfo() {
        Toast.makeText(
            context,
            R.string.grade_info,
            Toast.LENGTH_LONG
        ).show()
    }
}