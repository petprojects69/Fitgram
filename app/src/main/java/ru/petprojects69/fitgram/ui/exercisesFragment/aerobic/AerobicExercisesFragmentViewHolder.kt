package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemAerobicExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment.OnItemExerciseClickListener

class AerobicExercisesFragmentViewHolder(private val binding: ItemAerobicExerciseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(exercise: ExerciseEntity, clickListener: OnItemExerciseClickListener) {
        binding.itemExerciseTitleTextView.text = exercise.name
        binding.itemExerciseDescriptionTextView.text = exercise.description
        exercise.poster?.let { binding.itemExerciseImageView.setImageResource(it) }
        itemView.setOnClickListener {
            clickListener.onItemExerciseClick(exercise)
        }
    }
}