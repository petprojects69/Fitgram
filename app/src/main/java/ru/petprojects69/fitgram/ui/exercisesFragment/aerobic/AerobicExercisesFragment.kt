package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.petprojects69.fitgram.AppFitgram
import ru.petprojects69.fitgram.databinding.FragmentAerobicExercisesBinding

class AerobicExercisesFragment : Fragment() {

    companion object {
        private const val ARG_COUNT = "aerobicEx"
        fun newInstance(counter: Int?): AerobicExercisesFragment {
            val fragment = AerobicExercisesFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentAerobicExercisesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AerobicExerciseFragmentViewModel by viewModels {
        AerobicExerciseFragmentViewModel.ExerciseFragmentViewModelFactory((activity?.application as AppFitgram).repository)
    }

    private val adapter = AerobicExerciseFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAerobicExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        viewModel.allAerobicExercise.observe(viewLifecycleOwner) { exercises ->
            exercises?.let {
                adapter.exerciseAerobicList = exercises.toMutableList()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}