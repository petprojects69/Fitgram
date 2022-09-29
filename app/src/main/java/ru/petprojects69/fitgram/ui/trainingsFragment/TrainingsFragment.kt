package ru.petprojects69.fitgram.ui.trainingsFragment

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
import ru.petprojects69.fitgram.databinding.FragmentTrainingsBinding
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.usecase.ItemActionCallback
import ru.petprojects69.fitgram.ui.ItemTouchHelperCallback
import ru.petprojects69.fitgram.ui.datingTrainingDialogFragment.DatingTrainingDialogFragment

class TrainingsFragment : Fragment(R.layout.fragment_trainings) {

    private val binding: FragmentTrainingsBinding by viewBinding()
    private val adapter = TrainingsAdapter(
        object : ItemActionCallback {
            override fun delete() {
                deleteTrainingFromStorage()
            }

            override fun update() {
                updateTrainingFromStorage()
            }

            override fun <T> itemClick(training: T) {
                onItemClick(training as TrainingEntity)
            }
        }
    )

    private val viewModel: TrainingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val action = TrainingsFragmentDirections.toDialogConstructorTraining()

        initAdapter()

        viewModel.allTrainings.observe(viewLifecycleOwner) {
            adapter.initialList(it)
            adapter.notifyDataSetChanged()
        }

        binding.createTrainingFab.setOnClickListener {
            findNavController().navigate(action)
        }
    }

    // TODO Написать реализацию во viewModel
    private fun updateTrainingFromStorage() {
        Toast.makeText(requireContext(), "Тренировка изменена", Toast.LENGTH_SHORT).show()
    }

    // TODO Написать реализацию во viewModel
    private fun deleteTrainingFromStorage() {
        Toast.makeText(requireContext(), "Тренировка удалена", Toast.LENGTH_SHORT).show()
    }

    private fun onItemClick(training: TrainingEntity) {
        // вызов дилога для проставки даты
            // кнопка сохранить (логика доступности с конструктора)
                // сохранить треню с датой в бд в DatedTraining
                // закрыть диалог
                // вернуться в timetable

        DatingTrainingDialogFragment(training).show(childFragmentManager, null)
    }

    private fun initAdapter() {
        binding.trainingsRecyclerView.adapter = adapter
        binding.trainingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(ItemTouchHelperCallback(this.resources))
            .attachToRecyclerView(binding.trainingsRecyclerView)
    }
}