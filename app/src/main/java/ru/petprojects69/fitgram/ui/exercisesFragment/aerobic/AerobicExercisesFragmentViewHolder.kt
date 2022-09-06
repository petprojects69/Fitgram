package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemAerobicExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity

class AerobicExercisesFragmentViewHolder(private val binding: ItemAerobicExerciseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(exercise: AerobicExerciseEntity) {
        binding.itemExerciseTitleTextView.text = exercise.name
        binding.itemExerciseDescriptionTextView.text = exercise.description
        exercise.poster?.let { binding.itemExerciseImageView.setImageResource(it) }
    }

}