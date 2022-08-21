package ru.petprojects69.fitgram.ui.trainingsFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.petprojects69.fitgram.AppFitgram
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentTrainingsBinding

class TrainingsFragment : Fragment(R.layout.fragment_trainings) {
    private val binding: FragmentTrainingsBinding by viewBinding()
    private val adapter = TrainingsAdapter()

    private val viewModel: TrainingsViewModel by viewModels {
        TrainingsViewModelFactory((activity?.application as AppFitgram).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        viewModel.allTrainings.observe(viewLifecycleOwner) {
            adapter.initialList(it)
        }

        // TODO переход во фрагмент создания тренировки
        binding.createTrainingFab.setOnClickListener {

        }
    }

    private fun initAdapter() {
        binding.trainingsRecyclerView.adapter = adapter
        binding.trainingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}