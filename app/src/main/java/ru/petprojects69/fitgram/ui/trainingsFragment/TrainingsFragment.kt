package ru.petprojects69.fitgram.ui.trainingsFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentTrainingsBinding
import ru.petprojects69.fitgram.ui.ItemActionCallback
import ru.petprojects69.fitgram.ui.ItemTouchHelperCallback

class TrainingsFragment : Fragment(R.layout.fragment_trainings) {
    private val binding: FragmentTrainingsBinding by viewBinding()
    private val adapter = TrainingsAdapter(object : ItemActionCallback {
        override fun delete() {
            deleteTrainingFromStorage()
        }

        override fun update() {
            updateTrainingFromStorage()
        }

        override fun itemClick() {
            onItemClick()
        }
    })

    private val viewModel: TrainingsViewModel by viewModel()

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

    // TODO Написать реализацию во viewModel
    private fun updateTrainingFromStorage() {
        Toast.makeText(requireContext(), "Тренировка изменена", Toast.LENGTH_SHORT).show()
    }

    // TODO Написать реализацию во viewModel
    private fun deleteTrainingFromStorage() {
        Toast.makeText(requireContext(), "Тренировка удалена", Toast.LENGTH_SHORT).show()
    }

    private fun onItemClick() {
        Toast.makeText(requireContext(), "Добавить тренировку в расписание", Toast.LENGTH_SHORT)
            .show()
    }

    private fun initAdapter() {
        binding.trainingsRecyclerView.adapter = adapter
        binding.trainingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(ItemTouchHelperCallback(this.resources))
            .attachToRecyclerView(binding.trainingsRecyclerView)
    }
}