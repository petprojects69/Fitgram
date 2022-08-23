package ru.petprojects69.fitgram.ui.timeTableFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTimetableBinding
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.ui.ItemTouchHelperAdapter
import ru.petprojects69.fitgram.ui.TrainingCallback

class TimeTableAdapter(private val trainingCallback: TrainingCallback) :
    RecyclerView.Adapter<TimeTableHolder>(), ItemTouchHelperAdapter {

    private var exerciseList: MutableList<Pair<PowerExerciseEntity, Boolean>> = mutableListOf()

    fun initData(exercise: List<PowerExerciseEntity>) {
        exerciseList.clear()
        for (item in exercise) {
            exerciseList.add(Pair(first = item, second = false))
            notifyItemInserted(exercise.size)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableHolder {
        val binding = ItemTimetableBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TimeTableHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: TimeTableHolder, position: Int) {
        holder.onChangeClick = {
            onItemUpdate(holder.absoluteAdapterPosition)
        }
        holder.onDeleteClick = {
            onItemRemove(holder.absoluteAdapterPosition)
        }
        holder.detailsClick = {
            detailsClick(holder.absoluteAdapterPosition)
        }
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onItemRemove(position: Int) {
        trainingCallback.deleteTraining()
        exerciseList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemUpdate(position: Int) {
        trainingCallback.updateTraining()
        notifyItemChanged(position)
    }

    override fun detailsClick(position: Int) {
        exerciseList[position] =
            exerciseList[position].let {
                it.first to !it.second
            }
        notifyItemChanged(position)
    }
}