package ru.petprojects69.fitgram.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.petprojects69.fitgram.AppFitgram
import ru.petprojects69.fitgram.databinding.FragmentExercisesBinding
import ru.petprojects69.fitgram.ui.exercisesFragment.ExerciseFragmentAdapter
import ru.petprojects69.fitgram.ui.exercisesFragment.ExerciseFragmentViewModel

class ExercisesFragment : Fragment() {

    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExerciseFragmentViewModel by viewModels {
        ExerciseFragmentViewModel.ExerciseFragmentViewModelFactory((activity?.application as AppFitgram).repository)
    }

    private val adapter = ExerciseFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentExercisesBinding.inflate(inflater, container, false)
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