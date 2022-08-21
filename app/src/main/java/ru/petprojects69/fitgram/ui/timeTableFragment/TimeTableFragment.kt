package ru.petprojects69.fitgram.ui.timeTableFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.petprojects69.fitgram.AppFitgram
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentTimetableBinding

/** Фрагмент отображает рассписание запланированных тренировок, FAB открывает фрагмент добавления тренировки*/

class TimeTableFragment : Fragment(R.layout.fragment_timetable) {

    private val binding: FragmentTimetableBinding by viewBinding()

    private val viewModel: TimeTableViewModel by viewModels {
        TimeTableViewModel.TimeTableViewModelFactory((activity?.application as AppFitgram).repository)
    }

    private val adapter = TimeTableAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        viewModel.allPowerExercise.observe(viewLifecycleOwner) { exercises ->
            exercises?.let {
                adapter.exerciseList = exercises
            }
        }
    }
}