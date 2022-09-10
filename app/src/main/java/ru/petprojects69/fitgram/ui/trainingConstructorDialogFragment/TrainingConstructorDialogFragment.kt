package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogTrainingConstructorBinding
import ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment.ExerciseChooserDialogFragment

class TrainingConstructorDialogFragment : DialogFragment() {

    private val binding: DialogTrainingConstructorBinding by viewBinding()
    private val adapter = TrainingConstructorAdapter()
    private val viewModel: TrainingConstructorViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_training_constructor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialogFullscreen()
        binding.recyclerView.adapter = adapter

        binding.cancelButton.setOnClickListener {
            dialog?.dismiss()
        }

        binding.addExerciseTextView.apply {
            setOnClickListener {
                ExerciseChooserDialogFragment().show(
                    childFragmentManager, ExerciseChooserDialogFragment.TAG
                )
            }
            performClick()
        }
    }

    private fun setDialogFullscreen() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    companion object {
        const val TAG = "ConstructorTrainingDialog"
    }
}

