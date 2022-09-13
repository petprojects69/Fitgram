package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogTrainingConstructorBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment.ExerciseChooserDialogFragment


class TrainingConstructorDialogFragment : DialogFragment(R.layout.dialog_training_constructor) {

    private val binding: DialogTrainingConstructorBinding by viewBinding()
    private val adapter = TrainingConstructorAdapter()
    private val viewModel: TrainingConstructorViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialogSize()
        binding.recyclerView.adapter = adapter

        val callback:((ExerciseEntity) -> Unit) = {
            adapter.addExCustom(binding, it)
        }

        binding.cancelButton.setOnClickListener {
            dialog?.dismiss()
        }

        binding.addExerciseTextView.apply {
            setOnClickListener {
                ExerciseChooserDialogFragment(callback).show(childFragmentManager, null)
            }
        }
    }

    private fun setDialogSize() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

}

