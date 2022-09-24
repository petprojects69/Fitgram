package ru.petprojects69.fitgram.ui.exercisesFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentSliderExercisesBinding
import ru.petprojects69.fitgram.ui.utils.ZoomOutPageTransformer

class SliderExercisesFragment : Fragment(R.layout.fragment_slider_exercises) {

    private lateinit var adapter: SliderExerciseAdapter
    private val binding: FragmentSliderExercisesBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SliderExerciseAdapter(this)
        binding.pager.adapter = adapter
        binding.pager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, type ->
            tab.text = tabNames(type)
        }.attach()

        binding.createExerciseFab.setOnClickListener {
            val action =
                SliderExercisesFragmentDirections.actionExerciseItemToExerciseConstructorDialogFragment()
            findNavController().navigate(action)
        }
    }

    private fun tabNames(type: Int): String? {
        when (type) {
            ExerciseNumber.POWER.number -> return getString(R.string.tab_name_power)
            ExerciseNumber.AEROBIC.number -> return getString(R.string.tab_name_aerobic)
        }
        return null
    }
}