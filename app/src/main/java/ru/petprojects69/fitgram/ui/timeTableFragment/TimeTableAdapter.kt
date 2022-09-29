package ru.petprojects69.fitgram.ui.timeTableFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTimetableBinding
import ru.petprojects69.fitgram.domain.entity.DatedTrainingEntity
import ru.petprojects69.fitgram.domain.usecase.ItemActionCallback
import ru.petprojects69.fitgram.domain.usecase.ItemTouchHelperAdapter

class TimeTableAdapter(private val itemActionCallback: ItemActionCallback) :
    RecyclerView.Adapter<TimeTableViewHolder>(), ItemTouchHelperAdapter {

    private var datedTrainingList: MutableList<Pair<DatedTrainingEntity, Boolean>> = mutableListOf()

    fun initData(datedTraining: List<DatedTrainingEntity>) {
        datedTrainingList.clear()
        for (item in datedTraining) {
            datedTrainingList.add(Pair(first = item, second = false))
            notifyItemInserted(datedTraining.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        val binding = ItemTimetableBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TimeTableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        holder.onChangeClick = {
            onItemUpdate(holder.absoluteAdapterPosition)
        }
        holder.onDeleteClick = {
            onItemRemove(holder.absoluteAdapterPosition)
        }
        holder.detailsClick = {
            detailsClick(holder.absoluteAdapterPosition)
        }
        holder.onItemClick = {
            onItemClick(holder.absoluteAdapterPosition)
        }
        holder.bind(datedTrainingList[position])
    }

    override fun getItemCount(): Int {
        return datedTrainingList.size
    }

    override fun onItemRemove(position: Int) {
        val id = datedTrainingList[position].first.datedTrainingId
        itemActionCallback.delete(id)

        datedTrainingList.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemUpdate(position: Int) {
        itemActionCallback.update()
        notifyItemChanged(position)
    }

    override fun detailsClick(position: Int) {
        datedTrainingList[position] =
            datedTrainingList[position].let {
                it.first to !it.second
            }
        notifyItemChanged(position)
    }

    override fun onItemClick(position: Int) {
        val datedTraining = datedTrainingList[position].first
        itemActionCallback.itemClick(datedTraining)
    }
}