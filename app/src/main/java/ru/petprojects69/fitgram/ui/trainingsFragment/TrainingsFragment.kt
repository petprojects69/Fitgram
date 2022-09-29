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

class TrainingsFragment : Fragment(R.layout.fragment_trainings) {

    private val binding: FragmentTrainingsBinding by viewBinding()
    private val adapter = TrainingsAdapter(
        object : ItemActionCallback {
            override fun delete(id: String) {
                viewModel.removeTraining(id)
                Toast.makeText(requireContext(), "Тренировка удалена", Toast.LENGTH_SHORT).show()
            }

            override fun update() {
                Toast.makeText(requireContext(), "Тренировка изменена", Toast.LENGTH_SHORT).show()
            }

            override fun <T> itemClick(training: T) {
                findNavController().navigate(
                    TrainingsFragmentDirections.toDatingTrainingDialogFragment(training as TrainingEntity)
                )
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

    private fun initAdapter() {
        binding.trainingsRecyclerView.adapter = adapter
        binding.trainingsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        ItemTouchHelper(ItemTouchHelperCallback(this.resources))
            .attachToRecyclerView(binding.trainingsRecyclerView)
    }
}