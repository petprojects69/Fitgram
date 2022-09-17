package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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
    private var preferences: SharedPreferences? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        setDialogSize()
        setSaveButtonEnablity()
        setHelperVisibility()
        initClickListeners()

    }

    private fun setHelperVisibility() {
        preferences = activity?.getPreferences(AppCompatActivity.MODE_PRIVATE)

        val isHelperVisible = preferences?.getBoolean(IS_HELPER_VISIBLE, true)
        if (isHelperVisible == true) {
            binding.helperContainer.visibility = VISIBLE
        } else {
            binding.helperContainer.visibility = GONE
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter
        ItemTouchHelper(ItemTouchHelperCallback(adapter))
            .attachToRecyclerView(binding.recyclerView)
    }

    private fun setDialogSize() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    private fun setSaveButtonEnablity() {

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

    }

    private fun initClickListeners() {

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

        binding.saveButton.setOnClickListener {
            val label = binding.trainingLabel.text.toString()

            viewModel.viewModelScope.launch {
                viewModel.saveTraining(
                    TrainingEntity(label = label, exerciseList = adapter.exCustomList)
                )
            }
            dialog?.dismiss()
        }

        adapter.clickListener = TrainingConstructorAdapter.ChangeEx { position ->
            val getExerciseFromDataBaseCallback: ((ExerciseEntity) -> Unit) = { exercise ->
                adapter.changeEx(position, exercise)
            }

            ExerciseChooserDialogFragment(getExerciseFromDataBaseCallback).show(
                childFragmentManager,
                null
            )
        }

        binding.dismissHelperTextView.setOnClickListener {
            binding.helperContainer.visibility = GONE
            preferences
                ?.edit()
                ?.putBoolean(IS_HELPER_VISIBLE, false)
                ?.apply()
        }
    }

    companion object {
        private val IS_HELPER_VISIBLE = "isHelperVisible"
    }
}

