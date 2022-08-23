package ru.petprojects69.fitgram.ui.timeTableFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.*
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.exercise.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercise.ExerciseList
import ru.petprojects69.fitgram.domain.entity.exercise.PowerExerciseEntity

class TimeTableAdapter : Adapter<ViewHolder>() {

    var exerciseList: MutableList<ExerciseList> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var exercisePowerList: MutableList<PowerExerciseEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var exerciseAerobicList: MutableList<AerobicExerciseEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int = when (exerciseList[position]) {
        is ExerciseList.PowerExercise -> R.layout.item_training
        is ExerciseList.AerobicExercise -> R.layout.item_training
        null -> throw IllegalStateException("Unknown view")
    }

    inner class PowerViewHolder(item: View) : ViewHolder(item) {
        private val binding = ItemTrainingBinding.bind(item)
        fun bind(exerciseItem: ExerciseList.PowerExercise) {
            binding.titleTextView.text = exerciseItem.exercise.exerciseBase.name
            binding.timeTextView.text = exerciseItem.exercise.numberOfRepetitions.toString()
        }
    }

    inner class AerobicViewHolder(item: View) : ViewHolder(item) {
        private val binding = ItemTrainingBinding.bind(item)
        fun bind(exerciseItem: ExerciseList.AerobicExercise) {
            binding.titleTextView.text = exerciseItem.exercise.exerciseBase.name
            binding.timeTextView.text = exerciseItem.exercise.leadTime.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.item_training -> PowerViewHolder(v)
            else -> PowerViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exerciseList[position]
        when(holder){
            is PowerViewHolder -> holder.bind(item as ExerciseList.PowerExercise)
            is AerobicViewHolder -> holder.bind(item as ExerciseList.AerobicExercise)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}