package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogExerciseChooserBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class ExerciseChooserDialogFragment(private val callback: ((ExerciseEntity) -> Unit)? = null) :
    DialogFragment(R.layout.dialog_exercise_chooser),
    SearchView.OnQueryTextListener {

    val binding: DialogExerciseChooserBinding by viewBinding()
    val viewModel: ExerciseChooserViewModel by viewModel()

    private val adapter = ExerciseChooserAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setDialogSize()

        binding.recyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(this@ExerciseChooserDialogFragment)

        viewModel.allExercises.observe(this) {
            adapter.exercises = it
        }

        adapter.clickListener = ExerciseChooserAdapter.OnItemClick {
            callback?.invoke(it)
            dialog?.dismiss()
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