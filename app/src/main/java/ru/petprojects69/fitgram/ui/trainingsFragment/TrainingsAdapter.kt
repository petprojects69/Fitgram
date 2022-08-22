package ru.petprojects69.fitgram.ui.trainingsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.ui.ItemTouchHelperAdapter
import ru.petprojects69.fitgram.ui.TrainingCallback

class TrainingsAdapter(private val trainingCallback: TrainingCallback) :
    RecyclerView.Adapter<TrainingsViewHolder>(), ItemTouchHelperAdapter {

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
        holder.onChangeClick = {
            onItemUpdate(holder.absoluteAdapterPosition)
        }
        holder.onDeleteClick = {
            onItemRemove(holder.absoluteAdapterPosition)
        }
        holder.bind(trainingsList[position])
    }

    override fun getItemCount(): Int = trainingsList.size

    override fun onItemRemove(position: Int) {
        trainingCallback.deleteTraining()
        trainingsList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemUpdate(position: Int) {
        trainingCallback.updateTraining()
        notifyItemChanged(position)
    }
}