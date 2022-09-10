package ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogFragmentDetailExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity


class DetailsExerciseDialogFragment : DialogFragment(R.layout.dialog_fragment_detail_exercise) {

    private val binding: DialogFragmentDetailExerciseBinding by viewBinding()
    private val viewModel: DetailsExerciseDialogFragmentViewModel by viewModel()

    override fun onStart() {
        super.onStart()

        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailsExerciseDialogFragmentArgs by navArgs()

        viewModel.getAerobicExerciseForId(args.idExercise).observe(viewLifecycleOwner) { ex ->
            renderData(ex)
        }
    }

    private fun renderData(ex: AerobicExerciseEntity) {
        binding.dialogExerciseTitleTextView.text = ex.name
        ex.poster?.let { binding.dialogExerciseImageView.setImageResource(it) }
        binding.dialogDescriptionExerciseTextView.text = ex.description
    }

}