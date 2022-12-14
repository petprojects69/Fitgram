package ru.petprojects69.fitgram.ui.timeTableFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentTimetableBinding
import ru.petprojects69.fitgram.domain.usecase.ItemActionCallback
import ru.petprojects69.fitgram.ui.ItemTouchHelperCallback

/** Фрагмент отображает рассписание запланированных тренировок, FAB открывает фрагмент добавления тренировки*/

class TimeTableFragment : Fragment(R.layout.fragment_timetable) {

    private val binding: FragmentTimetableBinding by viewBinding()

    private val viewModel: TimeTableViewModel by viewModel()

    private val trainingCallback = object : ItemActionCallback {
        override fun delete(id: String) {
            Toast.makeText(requireContext(), "Тренировка удалена", Toast.LENGTH_SHORT).show()
            viewModel.removeDatedTraining(id)
        }

        override fun update() {
            Toast.makeText(requireContext(), "Тренировка изменена", Toast.LENGTH_SHORT).show()
        }

        override fun <T> itemClick(training: T) {
            Toast.makeText(requireContext(), "Начинаем тренировку", Toast.LENGTH_SHORT).show()
        }
    }

    private val adapter = TimeTableAdapter(trainingCallback)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        viewModel.datedTrainings.observe(viewLifecycleOwner) {
            adapter.initData(it)
            adapter.notifyDataSetChanged()
        }

        binding.addTrainingFab.setOnClickListener {
            findNavController().navigate(TimeTableFragmentDirections.toTrainingItem())
            Toast.makeText(requireContext(), "Выберите тренировку", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initAdapter() {
        binding.exerciseRecyclerView.adapter = adapter
        binding.exerciseRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(ItemTouchHelperCallback(requireContext().resources))
            .attachToRecyclerView(binding.exerciseRecyclerView)
    }
}