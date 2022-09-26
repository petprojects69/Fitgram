package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemAerobicExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.ui.detailExerciseDialogFragment.OnItemExerciseClickListener

class AerobicExercisesFragmentViewHolder(private val binding: ItemAerobicExerciseBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(exercise: ExerciseEntity, clickListener: OnItemExerciseClickListener) {
        binding.itemExerciseTitleTextView.text = exercise.name
        binding.itemExerciseDescriptionTextView.text = exercise.description
        if (exercise.posterCustom != null) {
            binding.itemExerciseImageView.setImageURI(Uri.parse("file://${exercise.posterCustom}"))
        } else {
            exercise.poster?.let { binding.itemExerciseImageView.setImageResource(it) }
        }
        itemView.setOnClickListener {
            clickListener.onItemExerciseClick(exercise.id)
        }
    }
}