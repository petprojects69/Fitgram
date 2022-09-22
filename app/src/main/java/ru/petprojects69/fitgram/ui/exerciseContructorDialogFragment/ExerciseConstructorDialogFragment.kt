package ru.petprojects69.fitgram.ui.exerciseContructorDialogFragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogExerciseConstructorBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType


class ExerciseConstructorDialogFragment : DialogFragment(R.layout.dialog_exercise_constructor) {

    private val binding: DialogExerciseConstructorBinding by viewBinding()
    private val viewModel: ExerciseConstructorDialogFragmentViewModel by viewModel()

    var types = arrayOf("Силовые", "Аэробные")

    override fun onStart() {
        super.onStart()
        initDialogWindowSize()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinnerTypes()
        initChangeListenerTextFolds()

        binding.constructorExerciseSaveButton.setOnClickListener {
            viewModel.viewModelScope.launch {
                viewModel.saveExercise(
                    ExerciseEntity(
                        name = binding.constructorExerciseLabelEditText.text.toString(),
                        description = binding.constructorExerciseDescriptionEditText.text.toString(),
                        type = when (binding.constructorExerciseLabelTypeSpinner.selectedItem) {
                            types[0] -> ExerciseType.POWER
                            types[1] -> ExerciseType.AEROBIC
                            else -> ExerciseType.POWER
                        }
                    )
                )
            }
            findNavController().popBackStack()
        }


    }

    private fun initChangeListenerTextFolds() {
        binding.constructorExerciseLabelEditText.addTextChangedListener {
            changeEnableSaveButton()
        }

        binding.constructorExerciseDescriptionEditText.addTextChangedListener {
            changeEnableSaveButton()
        }
    }

    private fun changeEnableSaveButton() {
        binding.constructorExerciseSaveButton.isEnabled =
            (!binding.constructorExerciseLabelEditText.text.isNullOrBlank()
                    and
                    !binding.constructorExerciseDescriptionEditText.text.isNullOrBlank())
    }

    private fun initSpinnerTypes() {
        val spinnerAdapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_item,
            types).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        with(binding.constructorExerciseLabelTypeSpinner) {
            adapter = spinnerAdapter
            setSelection(0)
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>, view: View?,
                        position: Int, id: Long,
                    ) {
                        binding.constructorExerciseImageCardView.strokeColor =
                            when (binding.constructorExerciseLabelTypeSpinner.selectedItem.toString()) {
                                types[1] -> ContextCompat.getColor(requireContext(),
                                    R.color.item_aerobic_image_card_color)
                                types[0] -> ContextCompat.getColor(requireContext(),
                                    R.color.item_power_image_card_color)
                                else -> {
                                    ContextCompat.getColor(requireContext(),
                                        R.color.item_power_image_card_color)
                                }
                            }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }
    }

    private fun initDialogWindowSize() {
        val dialog: Dialog? = dialog
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }
}
