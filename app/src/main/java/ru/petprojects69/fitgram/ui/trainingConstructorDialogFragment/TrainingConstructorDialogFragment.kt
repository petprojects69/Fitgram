package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.ItemTouchHelper
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogTrainingConstructorBinding
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment.ExerciseChooserDialogFragment


class TrainingConstructorDialogFragment : DialogFragment(R.layout.dialog_training_constructor) {

    private val binding: DialogTrainingConstructorBinding by viewBinding()
    private val adapter = TrainingConstructorAdapter()
    private val viewModel: TrainingConstructorViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        setDialogSize()

        ItemTouchHelper(ItemTouchHelperCallback(adapter))
            .attachToRecyclerView(binding.recyclerView)


        binding.cancelButton.setOnClickListener {
            dialog?.dismiss()
        }

        binding.addExerciseButton.setOnClickListener {
            val getExerciseFromDataBaseCallback: ((ExerciseEntity) -> Unit) = {
                adapter.addExCustom(binding, it)
            }

            ExerciseChooserDialogFragment(getExerciseFromDataBaseCallback).show(
                childFragmentManager,
                null
            )
        }

        adapter.clickListener = TrainingConstructorAdapter.ChangeEx { position ->
            val getExerciseFromDataBaseCallback: ((ExerciseEntity) -> Unit) = {exercise ->
                adapter.changeEx(position, exercise)
            }

            ExerciseChooserDialogFragment(getExerciseFromDataBaseCallback).show(
                childFragmentManager,
                null
            )
        }

        if (binding.trainingLabel.text.toString() == "") {
            binding.saveButton.isEnabled = false
        } else {
            binding.saveButton.isEnabled = true
        }

        binding.trainingLabel.addTextChangedListener {
            if (it.isNullOrBlank()) {
                binding.saveButton.isEnabled = false
            } else {
                binding.saveButton.isEnabled = true
            }
        }

        binding.saveButton.setOnClickListener {
            val label = binding.trainingLabel.text.toString()

            viewModel.viewModelScope.launch {
                viewModel.saveTraining(
                    TrainingEntity(label = label, exerciseList = adapter.exCustomList)
                )
            }
            dialog?.dismiss()
        }
    }

    private fun setDialogSize() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

}

