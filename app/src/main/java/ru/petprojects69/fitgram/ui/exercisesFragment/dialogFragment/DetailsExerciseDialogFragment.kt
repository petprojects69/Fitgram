package ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogFragmentDetailExerciseBinding

class DetailsExerciseDialogFragment : DialogFragment(R.layout.dialog_fragment_detail_exercise) {

    private val binding: DialogFragmentDetailExerciseBinding by viewBinding()
    private val viewModel: DetailsExerciseDialogFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailsExerciseDialogFragmentArgs by navArgs()
        val id = args.idExercise

        viewModel.getAerobicExerciseForId(id).observe(viewLifecycleOwner) { ex ->
            binding.dialogExerciseTitleTextView.text = ex.name
        }

    }

}