package ru.petprojects69.fitgram.ui.exercisesFragment.power

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemPowerExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

class PowerExerciseFragmentAdapter :
    RecyclerView.Adapter<PowerExerciseFragmentAdapter.ExerciseFragmentHolder>() {

    var exercisePowerList: MutableList<PowerExerciseEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ExerciseFragmentHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPowerExerciseBinding.bind(item)
        fun bind(exercise: PowerExerciseEntity) {
            binding.itemExerciseTitleTextView.text = exercise.exercise.name
            binding.itemExerciseDescriptionTextView.text = exercise.exercise.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseFragmentHolder =
        ExerciseFragmentHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_power_exercise, parent, false)
        )

    override fun onBindViewHolder(holder: ExerciseFragmentHolder, position: Int) {
        holder.bind(exercisePowerList[position])
    }

    override fun getItemCount(): Int {
        return exercisePowerList.size
    }
}