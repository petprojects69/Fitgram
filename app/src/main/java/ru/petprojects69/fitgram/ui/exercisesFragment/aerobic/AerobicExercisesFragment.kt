package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentAerobicExercisesBinding

class AerobicExercisesFragment : Fragment(R.layout.fragment_aerobic_exercises) {

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

    private val binding: FragmentAerobicExercisesBinding by viewBinding()
    private val viewModel: AerobicExercisesFragmentViewModel by viewModel()
    private val adapter = AerobicExercisesFragmentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        viewModel.allAerobicExercise.observe(viewLifecycleOwner) {
            adapter.exerciseAerobicList = it
        }
    }
}