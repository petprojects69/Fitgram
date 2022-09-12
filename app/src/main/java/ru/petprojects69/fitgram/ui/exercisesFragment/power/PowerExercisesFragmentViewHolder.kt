package ru.petprojects69.fitgram.ui.exercisesFragment.power

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemPowerExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class PowerExercisesFragmentViewHolder(private val binding: ItemPowerExerciseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(exercise: ExerciseEntity) {
    fun bind(exercise: PowerExerciseEntity, clickListener: OnItemExerciseClickListener) {
        binding.itemExerciseTitleTextView.text = exercise.name
        binding.itemExerciseDescriptionTextView.text = exercise.description
        exercise.poster?.let { binding.itemExerciseImageView.setImageResource(it) }
        itemView.setOnClickListener {
            clickListener.onItemExerciseClick(exercise)
        }
    }
}