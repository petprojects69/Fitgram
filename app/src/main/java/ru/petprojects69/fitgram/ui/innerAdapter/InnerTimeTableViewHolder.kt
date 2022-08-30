package ru.petprojects69.fitgram.ui.innerAdapter

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.InnerItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.BasicExercise

class InnerTimeTableViewHolder(private val binding: InnerItemTrainingBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(exercise: BasicExercise) {
        binding.exerciseLabelTextView.text =
            exercise.name
    }
}