package ru.petprojects69.fitgram.ui.innerAdapter

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.InnerItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized

class InnerTimeTableViewHolder(private val binding: InnerItemTrainingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(exercise: ExerciseCustomized) {

        if (exercise.exAerobic != null) {
            binding.exerciseLabelTextView.text = exercise.exAerobic.name.toString()
            binding.durationTextView.text = exercise.duration.toString()

        } else {
            binding.exerciseLabelTextView.text = exercise.exPower!!.name.toString()
            binding.exerciseSetsTextView.text = exercise.sets.toString()
            binding.exerciseRepsTextView.text = exercise.reps.toString()
        }
    }
}