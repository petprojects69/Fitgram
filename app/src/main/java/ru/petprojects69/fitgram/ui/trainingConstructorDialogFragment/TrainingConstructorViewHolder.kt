package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.content.Context
import android.opengl.Visibility
import android.view.View.GONE
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemTrainingConstructorBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType

class TrainingConstructorViewHolder(private val binding: ItemTrainingConstructorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val context: Context = binding.root.context

    fun bind(exCustomized: ExerciseCustomized) {

        binding.exerciseTitleEditText.text = exCustomized.exerciseInitial.name

        when (exCustomized.exerciseInitial.type){
            ExerciseType.AEROBIC -> {
                binding.itemExerciseBaseCardView.strokeColor = context.getColor(R.color.item_aerobic_exercise_stroke_color)
                binding.exerciseSetsEditText.visibility = GONE
                binding.charBetweenSetsAndRepsTextView.visibility = GONE
                binding.exerciseRepsOrDurationTextView.hint = "Длительность"
            }
            ExerciseType.POWER -> {
                binding.itemExerciseBaseCardView.strokeColor = context.getColor(R.color.item_power_exercise_stroke_color)
                binding.charBetweenSetsAndRepsTextView.text = context.getString(R.string.char_between_sets_and_reps_textView)
                binding.exerciseRepsOrDurationTextView.hint = "Повторы"
            }
        }
    }
}
