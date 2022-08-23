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

    private var trainingsList: MutableList<Pair<PowerExerciseEntity, Boolean>> = mutableListOf()

    // TODO изменить ExerciseEntity на Тренировка
    fun initialList(list: MutableList<PowerExerciseEntity>) {
        trainingsList.clear()
        for (item in list) {
            val position = trainingsList.size
            trainingsList.add(position, Pair(first = item, second = false))
            notifyItemInserted(position)
        }
    }

    // TODO изменить ExerciseEntity на Тренировка
    fun addTraining(training: PowerExerciseEntity) {
        val position = trainingsList.size
        trainingsList.add(position, Pair(first = training, second = false))
        notifyItemInserted(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingsViewHolder {
        val binding = ItemTrainingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: TrainingsViewHolder, position: Int) {
        holder.onChangeClick = {
            onItemUpdate(holder.absoluteAdapterPosition)
        }
        holder.onDeleteClick = {
            onItemRemove(holder.absoluteAdapterPosition)
        }
        holder.detailsClick = {
            detailsClick(holder.absoluteAdapterPosition)
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

    override fun detailsClick(position: Int) {
        trainingsList[position] =
            trainingsList[position].let {
                it.first to !it.second
            }
        notifyItemChanged(position)
    }
}