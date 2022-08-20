package ru.petprojects69.fitgram.ui.timeTableFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.ui.timeTableFragment.TimeTableAdapter.TimeTableHolder

class TimeTableAdapter : RecyclerView.Adapter<TimeTableHolder>() {

    var exerciseList: MutableList<PowerExerciseEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class TimeTableHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemTrainingBinding.bind(item)
        fun bind(exercise: PowerExerciseEntity) {
            binding.titleTextView.text = exercise.exercise.name
            binding.timeTextView.text = exercise.numberOfRepetitions.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableHolder =
        TimeTableHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_training, parent, false)
        )

    override fun onBindViewHolder(holder: TimeTableHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}