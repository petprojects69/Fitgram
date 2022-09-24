package ru.petprojects69.fitgram.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentMainBinding
import ru.petprojects69.fitgram.ui.MainActivity.Companion.PREF_USER_ID_KEY

class MainFragment : Fragment(R.layout.fragment_main) {
    private val binding: FragmentMainBinding by viewBinding()
    private val bottomNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationView }
    private val navigationController by lazy {
        requireActivity().findNavController(R.id.navigation_fragment_container)
    }
    private var userId: String? = null
    private val preferences: SharedPreferences by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigation()
        userId = preferences.getString(PREF_USER_ID_KEY, null)
    }

    private fun initBottomNavigation() {
        binding.labelFragmentTextView.text = resources.getString(R.string.label_timetable)
        bottomNavigationPanel.setupWithNavController(navigationController)
        bottomNavigationPanel.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.timetable_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_timetable)
                    navigationController.navigate(R.id.timetable_item)
                    true
                }
                R.id.training_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_trainings)
                    navigationController.navigate(R.id.training_item)
                    true
                }
                R.id.exercise_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_exercises)
                    navigationController.navigate(R.id.exercise_item)
                    true
                }

                R.id.profile_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_profile)
                    navigationController.navigate(R.id.profile_item)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}