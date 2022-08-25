package ru.petprojects69.fitgram.ui.exercisesFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentSliderExercisesBinding
import ru.petprojects69.fitgram.ui.utils.ZoomOutPageTransformer

class SliderExercisesFragment : Fragment() {

    private lateinit var adapter: SliderExerciseAdapter

    private var _binding: FragmentSliderExercisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSliderExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SliderExerciseAdapter(this)
        binding.pager.adapter = adapter
        binding.pager.setPageTransformer(ZoomOutPageTransformer())

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, type ->
            tab.text = tabNames(type)
        }.attach()
    }

    private fun tabNames(type: Int): String? {
        when (type) {
            ExerciseNumber.POWER.number -> return getString(R.string.tab_name_power)
            ExerciseNumber.AEROBIC.number -> return getString(R.string.tab_name_aerobic)
        }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}