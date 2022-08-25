package ru.petprojects69.fitgram.ui.exercisesFragment.power

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.petprojects69.fitgram.AppFitgram
import ru.petprojects69.fitgram.databinding.FragmentPowerExercisesBinding

class PowerExercisesFragment : Fragment() {

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

    private var _binding: FragmentPowerExercisesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PowerExerciseFragmentViewModel by viewModels {
        PowerExerciseFragmentViewModel.ExerciseFragmentViewModelFactory((activity?.application as AppFitgram).repository)
    }

    private val adapter = PowerExerciseFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentPowerExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        viewModel.allPowerExercise.observe(viewLifecycleOwner) { exercises ->
            exercises?.let {
                adapter.exercisePowerList = exercises.toMutableList()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}