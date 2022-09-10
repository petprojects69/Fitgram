package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.DialogExerciseChooserBinding

class ExerciseChooserDialogFragment : DialogFragment(), SearchView.OnQueryTextListener {

    val binding: DialogExerciseChooserBinding by viewBinding()
    val viewModel: ExerciseChooserViewModel by viewModel()

    private val adapter = ExerciseChooserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.dialog_exercise_chooser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )


        binding.recyclerView.adapter = adapter
        binding.searchView.apply {
            onActionViewExpanded()
            setOnQueryTextListener(this@ExerciseChooserDialogFragment)
        }

        viewModel.allExercises.observe(this) {
            adapter.exNameList = it
        }
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
            adapter.exNameList = it
        }
    }


    companion object {
        const val TAG = "ExerciseChooserDialog"
    }

}