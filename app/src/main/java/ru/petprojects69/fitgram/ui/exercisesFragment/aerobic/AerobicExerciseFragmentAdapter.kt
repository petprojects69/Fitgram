package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemAerobicExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity

class AerobicExerciseFragmentAdapter :
    RecyclerView.Adapter<AerobicExerciseFragmentAdapter.ExerciseFragmentHolder>() {

    var exerciseAerobicList: MutableList<AerobicExerciseEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ExerciseFragmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemAerobicExerciseBinding.bind(item)
        fun bind(exercise: AerobicExerciseEntity) {
            binding.itemExerciseTitleTextView.text = exercise.exercise.name
            binding.itemExerciseDescriptionTextView.text = exercise.exercise.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseFragmentHolder =
        ExerciseFragmentHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_aerobic_exercise, parent, false)
        )

    override fun onBindViewHolder(holder: ExerciseFragmentHolder, position: Int) {
        holder.bind(exerciseAerobicList[position])
    }

    override fun getItemCount(): Int {
        return exerciseAerobicList.size
    }
}