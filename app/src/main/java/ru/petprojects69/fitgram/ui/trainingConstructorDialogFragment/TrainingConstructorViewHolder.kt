package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.petprojects69.fitgram.databinding.ItemTrainingConstructorBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized

class TrainingConstructorViewHolder(private val binding: ItemTrainingConstructorBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun bind(exCustomized: ExerciseCustomized) {
//        binding.exerciseTitleEditText.addTextChangedListener {
//            scope.launch {
//                repository.findExercise(it.toString())
//            }
//        }
    }
}
