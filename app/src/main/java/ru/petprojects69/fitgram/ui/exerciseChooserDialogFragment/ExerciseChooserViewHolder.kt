package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemExerciseChooserBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType

class ExerciseChooserViewHolder(
    private val binding: ItemExerciseChooserBinding,
    private val clickListener: ExerciseChooserAdapter.OnItemClick?,
) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context = binding.root.context

    fun bind(exercise: ExerciseEntity) {
        binding.exerciseName.apply {
            text = exercise.name
            setOnClickListener {
                clickListener?.onClick(exercise)
            }
        }

        when (exercise.type) {
            ExerciseType.AEROBIC -> binding.exerciseName.setBackgroundColor(context.getColor(R.color.item_aerobic_exercise_background_color))
            ExerciseType.POWER -> binding.exerciseName.setBackgroundColor(context.getColor(R.color.item_power_exercise_background_color))
        }
    }
}
