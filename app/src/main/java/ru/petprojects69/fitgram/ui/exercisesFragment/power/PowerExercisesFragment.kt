package ru.petprojects69.fitgram.ui.exercisesFragment.power

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentPowerExercisesBinding

class PowerExercisesFragment : Fragment(R.layout.fragment_power_exercises) {

    companion object {
        private const val ARG_COUNT = "powerEx"
        fun newInstance(counter: Int?): PowerExercisesFragment {
            val fragment = PowerExercisesFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
    }

    private val binding: FragmentPowerExercisesBinding by viewBinding()
    private val viewModel: PowerExerciseFragmentViewModel by viewModel()
    private val adapter = PowerExerciseFragmentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        viewModel.allPowerExercise.observe(viewLifecycleOwner) { exercises ->
            exercises?.let {
                adapter.exercisePowerList = exercises.toMutableList()
            }
        }
    }
}