package ru.petprojects69.fitgram.ui.datingTrainingDialogFragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogDatingTrainingBinding
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

class DatingTrainingDialogFragment(private val training: TrainingEntity) :
    DialogFragment(R.layout.dialog_dating_training) {

    private val binding: DialogDatingTrainingBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setDialogSize()
        binding.trainingLabel.text = training.label
    }

    private fun setDialogSize() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}