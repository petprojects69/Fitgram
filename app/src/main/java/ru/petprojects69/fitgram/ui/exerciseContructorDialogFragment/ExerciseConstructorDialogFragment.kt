package ru.petprojects69.fitgram.ui.exerciseContructorDialogFragment

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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
import okio.FileNotFoundException
import okio.IOException
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogExerciseConstructorBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType
import java.io.File
import java.io.FileOutputStream


class ExerciseConstructorDialogFragment : DialogFragment(R.layout.dialog_exercise_constructor) {


    companion object {
        private var selectedImage: Uri? = null
        private var selectedExerciseBitmap: Bitmap? = null
        private var pathExercisePoster: String? = null
        private const val GALLERY_PERMISSION_REQUEST = 1
        private const val GALLERY_RESULT_REQUEST = 2
        private var types = arrayOf("Силовые", "Аэробные")
    }

    private val binding: DialogExerciseConstructorBinding by viewBinding()
    private val viewModel: ExerciseConstructorDialogFragmentViewModel by viewModel()


    override fun onStart() {
        super.onStart()
        initDialogWindowSize()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinnerTypes()
        initChangeListenerTextFolds()
        initActionButton()
        initPosterSelector()
    }

    private fun initPosterSelector() {
        binding.constructorExerciseImageView.setOnClickListener() {
            selectImage()
        }
    }

    private fun selectImage() {
        activity?.let {
            if (ContextCompat.checkSelfPermission(it.applicationContext,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    GALLERY_PERMISSION_REQUEST)
            } else {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY_RESULT_REQUEST)
            }
        }
    }


    private fun initActionButton() {
        binding.constructorExerciseCancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.constructorExerciseSaveButton.setOnClickListener {
            saveExerciseImage()
            viewModel.viewModelScope.launch {
                viewModel.saveExercise(
                    ExerciseEntity(
                        name = binding.constructorExerciseLabelEditText.text.toString(),
                        description = binding.constructorExerciseDescriptionEditText.text.toString(),
                        type = when (binding.constructorExerciseLabelTypeSpinner.selectedItem) {
                            types[0] -> ExerciseType.POWER
                            types[1] -> ExerciseType.AEROBIC
                            else -> ExerciseType.POWER
                        },
                        posterCustom = pathExercisePoster
                    )
                )
            }
            findNavController().popBackStack()
        }
    }

    private fun saveExerciseImage() {
        try {
            val path =
                File(requireContext().filesDir, File.separator + "Images")
            if (!path.exists()) {
                path.mkdirs()
            }
            val outFile =
                File(path, "${binding.constructorExerciseLabelEditText.text}.jpeg")

            val outputStream = FileOutputStream(outFile)
            selectedExerciseBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.close()
        } catch (e: FileNotFoundException) {
            Log.e(TAG, "Saving received message failed with", e)
        } catch (e: IOException) {
            Log.e(TAG, "Saving received message failed with", e)
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

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        if (requestCode == GALLERY_PERMISSION_REQUEST) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, GALLERY_RESULT_REQUEST)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_RESULT_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage = data.data
        }
        try {
            context?.let {
                if (selectedImage != null) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        val source = ImageDecoder.createSource(it.contentResolver, selectedImage!!)
                        selectedExerciseBitmap = ImageDecoder.decodeBitmap(source)
                        binding.constructorExerciseImageView.setImageBitmap(selectedExerciseBitmap)
                    } else {
                        selectedExerciseBitmap =
                            MediaStore.Images.Media.getBitmap(it.contentResolver, selectedImage)
                        binding.constructorExerciseImageView.setImageBitmap(selectedExerciseBitmap)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
