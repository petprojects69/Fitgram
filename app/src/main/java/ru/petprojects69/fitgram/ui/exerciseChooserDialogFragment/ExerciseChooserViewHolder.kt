package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemExerciseChooserBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class ExerciseChooserViewHolder(
    private val binding: ItemExerciseChooserBinding,
    private val clickListener: ExerciseChooserAdapter.OnItemClick?,
) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(exercise: ExerciseEntity) {
        binding.exerciseName.apply {
            text = exercise.name
            setOnClickListener {
                clickListener?.onClick(exercise)
            }
        }
    }
}
