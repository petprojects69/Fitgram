package ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogFragmentDetailExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType


class DetailsExerciseDialogFragment :
    DialogFragment(R.layout.dialog_fragment_detail_exercise) {

    private val binding: DialogFragmentDetailExerciseBinding by viewBinding()
    private val viewModel: DetailsExerciseDialogFragmentViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailsExerciseDialogFragmentArgs by navArgs()

        viewModel.getAerobicExerciseForId(args.idExercise).observe(viewLifecycleOwner) { ex ->
            binding.dialogExerciseTitleTextView.text = ex.name
            ex.poster?.let { binding.dialogExerciseImageView.setImageResource(it) }
            binding.dialogDescriptionExerciseTextView.text = ex.description

            binding.dialogExerciseImageCardView.strokeColor =
                when (ex.type) {
                    ExerciseType.AEROBIC -> getColor(requireContext(),
                        R.color.item_aerobic_image_card_color)
                    ExerciseType.POWER -> getColor(requireContext(),
                        R.color.item_power_image_card_color)
                }
        }

        binding.deleteExerciseImageButton.setOnClickListener {
            findNavController().popBackStack()
            viewModel.removeExerciseForId(args.idExercise)
        }
    }
}