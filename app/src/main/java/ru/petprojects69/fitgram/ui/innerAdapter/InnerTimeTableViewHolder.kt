package ru.petprojects69.fitgram.ui.innerAdapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.InnerItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized

class InnerTimeTableViewHolder(private val binding: InnerItemTrainingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val context: Context = binding.root.context

    fun bind(exercise: ExerciseCustomized) {

        if (exercise.exAerobic != null) {
            binding.exerciseTitleTextView.text = exercise.exAerobic.name.toString()
            binding.exerciseRepsTextView.text = exercise.duration.toString()
            binding.itemInnerCardView.strokeColor =
                context.getColor(R.color.item_aerobic_exercise_stroke_color)

        } else {
            binding.exerciseTitleTextView.text = exercise.exPower!!.name.toString()
            binding.exerciseSetsTextView.text = exercise.sets.toString()
            binding.exerciseRepsTextView.text = exercise.reps.toString()
            binding.charBetweenSetsAndRepsTextView.text =
                context.getString(R.string.char_between_sets_and_reps_textView)
            binding.itemInnerCardView.strokeColor =
                context.getColor(R.color.item_power_exercise_stroke_color)
        }
    }
}