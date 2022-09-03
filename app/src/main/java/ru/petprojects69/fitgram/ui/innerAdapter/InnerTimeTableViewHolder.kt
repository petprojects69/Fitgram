package ru.petprojects69.fitgram.ui.innerAdapter

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.InnerItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.TrainingExercise

class InnerTimeTableViewHolder(private val binding: InnerItemTrainingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(exercise: TrainingExercise) {
        binding.exerciseLabelTextView.text =
            exercise.name
    }
}