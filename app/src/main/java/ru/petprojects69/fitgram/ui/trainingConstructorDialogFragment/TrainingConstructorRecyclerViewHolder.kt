package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemPowerExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

class TrainingConstructorRecyclerViewHolder(private val binding: ItemPowerExerciseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(exercise: PowerExerciseEntity) {
        binding.itemExerciseTitleTextView.text = exercise.name
        binding.itemExerciseDescriptionTextView.text = exercise.description
        exercise.poster?.let { binding.itemExerciseImageView.setImageResource(it) }
    }
}