package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogTrainingConstructorBinding

class TrainingConstructorDialogFragment : DialogFragment(R.layout.dialog_training_constructor) {

    private val binding: DialogTrainingConstructorBinding by viewBinding()
    private val adapter = TrainingConstructorAdapter()
    private val viewModel: TrainingConstructorViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val action = TrainingConstructorDialogFragmentDirections.toDialogExerciseChooser()

        setDialogSize()
        binding.recyclerView.adapter = adapter

        binding.cancelButton.setOnClickListener {
            dialog?.dismiss()
        }

        binding.addExerciseTextView.apply {
            setOnClickListener {
                findNavController().navigate(action)
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

