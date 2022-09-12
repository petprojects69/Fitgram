package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogExerciseChooserBinding
import ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment.TrainingConstructorDialogFragmentDirections

class ExerciseChooserDialogFragment : DialogFragment(R.layout.dialog_exercise_chooser), SearchView.OnQueryTextListener {

    val binding: DialogExerciseChooserBinding by viewBinding()
    val viewModel: ExerciseChooserViewModel by viewModel()

    private val adapter = ExerciseChooserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val action = ExerciseChooserDialogFragmentDirections.toDialogConstructorTraining()
        setDialogSize()

        binding.recyclerView.adapter = adapter
        binding.searchView.apply {
            onActionViewExpanded()
            setOnQueryTextListener(this@ExerciseChooserDialogFragment)
        }

        viewModel.allExercises.observe(this) {
            adapter.exercises = it
        }

        adapter.clickListener =  ExerciseChooserAdapter.OnItemClick {
            findNavController().navigate(action)
        }
    }

    private fun setDialogSize() {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchExercise(query)
        }
        return true
    }


    private fun searchExercise(query: String) {
        val searchQuery = "%$query%"
        viewModel.searchExercise(searchQuery).observe(this) {
            adapter.exercises = it
        }
    }

}