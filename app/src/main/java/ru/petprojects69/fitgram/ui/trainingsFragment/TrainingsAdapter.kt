package ru.petprojects69.fitgram.ui.trainingsFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.ui.ItemActionCallback
import ru.petprojects69.fitgram.ui.ItemTouchHelperAdapter

class TrainingsAdapter(private val itemActionCallback: ItemActionCallback) :
    RecyclerView.Adapter<TrainingsViewHolder>(), ItemTouchHelperAdapter {

    private var trainingsList: MutableList<Pair<TrainingEntity, Boolean>> = mutableListOf()

    // TODO изменить ExerciseEntity на Тренировка
    fun initialList(list: MutableList<TrainingEntity>) {
        trainingsList.clear()
        for (item in list) {
            val position = trainingsList.size
            trainingsList.add(position, Pair(first = item, second = false))
            notifyItemInserted(position)
        }
    }

    // TODO изменить ExerciseEntity на Тренировка
    fun addTraining(training: TrainingEntity) {
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
        holder.itemClick = {
            onItemClick(holder.absoluteAdapterPosition)
        }
        holder.bind(trainingsList[position])
    }

    override fun getItemCount(): Int = trainingsList.size

    override fun onItemRemove(position: Int) {
        itemActionCallback.delete()
        trainingsList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemUpdate(position: Int) {
        itemActionCallback.update()
        notifyItemChanged(position)
    }

    override fun detailsClick(position: Int) {
        trainingsList[position] =
            trainingsList[position].let {
                it.first to !it.second
            }
        notifyItemChanged(position)
    }

    override fun onItemClick(position: Int) {
        itemActionCallback.itemClick()
    }
}