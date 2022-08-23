package ru.petprojects69.fitgram.ui.timeTableFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.petprojects69.fitgram.AppFitgram
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentTimetableBinding
import ru.petprojects69.fitgram.ui.ItemTouchHelperCallback
import ru.petprojects69.fitgram.ui.TrainingCallback

/** Фрагмент отображает рассписание запланированных тренировок, FAB открывает фрагмент добавления тренировки*/

class TimeTableFragment : Fragment(R.layout.fragment_timetable) {

    private val binding: FragmentTimetableBinding by viewBinding()

    private val viewModel: TimeTableViewModel by viewModels {
        TimeTableViewModel.TimeTableViewModelFactory((activity?.application as AppFitgram).repository)
    }

    private val trainingCallback = object : TrainingCallback {
        override fun deleteTraining() {
            Toast.makeText(requireContext(), "Тренировка удалена", Toast.LENGTH_SHORT).show()
        }

        override fun updateTraining() {
            Toast.makeText(requireContext(), "Тренировка изменена", Toast.LENGTH_SHORT).show()
        }
    }

    private val adapter = TimeTableAdapter(trainingCallback)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        viewModel.allPowerExercise.observe(viewLifecycleOwner) { exercises ->
            exercises?.let {
                adapter.initData(exercises)
            }
        }
    }

    private fun initAdapter() {
        binding.exerciseRecyclerView.adapter = adapter
        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(ItemTouchHelperCallback(requireContext().resources))
            .attachToRecyclerView(binding.exerciseRecyclerView)
    }
}