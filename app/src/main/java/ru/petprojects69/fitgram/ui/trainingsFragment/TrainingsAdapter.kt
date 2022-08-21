package ru.petprojects69.fitgram.ui.trainingsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity

class TrainingsAdapter : RecyclerView.Adapter<TrainingsViewHolder>() {

    var trainingsList: MutableList<PowerExerciseEntity> = mutableListOf()

    // TODO изменить ExerciseEntity на Тренировка
    fun initialList(list: MutableList<PowerExerciseEntity>) {
        trainingsList.clear()
        for (item in list) {
            val position = trainingsList.size
            trainingsList.add(position, item)
            notifyItemInserted(position)
        }
    }

    // TODO изменить ExerciseEntity на Тренировка
    fun addTraining(training: PowerExerciseEntity) {
        val position = trainingsList.size
        trainingsList.add(position, training)
        notifyItemInserted(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingsViewHolder {
        val binding = ItemTrainingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingsViewHolder, position: Int) {
        holder.bind(trainingsList[position])
    }

    override fun getItemCount(): Int = trainingsList.size
}