package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemExerciseChooserBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class ExerciseChooserViewHolder(private val binding: ItemExerciseChooserBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(exName: ExerciseEntity) {
        binding.exerciseName.text = exName.name
    }
}
