package ru.petprojects69.fitgram.ui.datingTrainingDialogFragment

import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogDatingTrainingBinding
import ru.petprojects69.fitgram.domain.entity.DatedTrainingEntity

class DatingTrainingDialogFragment :
    DialogFragment(R.layout.dialog_dating_training) {

    private val binding: DialogDatingTrainingBinding by viewBinding()
    private val viewModel: DatingTrainingDialogFragmentViewModel by viewModel()
    private val args: DatingTrainingDialogFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.trainingLabel.text = args.training.label.toString()
        setDialogSize()
        initClickListeners()

    }

    private fun setDialogSize() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun initClickListeners() {

        binding.cancelButton.setOnClickListener {
            dialog?.dismiss()
        }

        binding.saveButton.setOnClickListener {
            val timestamp = toTimeStamp()

            viewModel.viewModelScope.launch {
                viewModel.saveDatedTraining(DatedTrainingEntity(training = args.training, date = timestamp))
            }
            findNavController().navigate(DatingTrainingDialogFragmentDirections.toTimetableItem())
        }
    }

    private fun toTimeStamp(): Long {
        val year = binding.datePicker.year
        val month = binding.datePicker.month
        val day = binding.datePicker.dayOfMonth

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)

        return calendar.timeInMillis
    }
}