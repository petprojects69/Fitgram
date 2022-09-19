package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.DialogTrainingConstructorBinding
import ru.petprojects69.fitgram.databinding.ItemTrainingConstructorBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.domain.usecase.ItemTouchHelperAdapter

class TrainingConstructorAdapter :
    RecyclerView.Adapter<TrainingConstructorViewHolder>(), ItemTouchHelperAdapter {

    var clickListener: ChangeEx? = null
    var exCustomList: MutableList<ExerciseCustomized> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TrainingConstructorViewHolder {

        val binding = ItemTrainingConstructorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingConstructorViewHolder(binding, clickListener)
    }

    fun addExCustom(binding: DialogTrainingConstructorBinding, exercise: ExerciseEntity) {
        val position = exCustomList.size

        exCustomList.add(ExerciseCustomized(exercise))
        binding.recyclerView.scrollToPosition(position)
        notifyItemInserted(position)
    }

    fun changeEx(position: Int, exercise: ExerciseEntity) {
        exCustomList.removeAt(position).apply {
            exCustomList.add(position, ExerciseCustomized(exercise))
        }
        notifyItemChanged(position)
    }

    override fun onBindViewHolder(holder: TrainingConstructorViewHolder, position: Int) {
        holder.bind(exCustomList[position])
    }

    override fun getItemCount(): Int {
        return exCustomList.size
    }

    override fun onItemMove(from: Int, to: Int) {
        exCustomList.removeAt(from).apply {
            exCustomList.add(
                if (to > from) {
                    to - 1
                } else {
                    to
                }, this
            )
        }
        notifyItemMoved(from, to)
    }

    override fun onItemRemove(position: Int) {
        exCustomList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun interface ChangeEx {
        fun onClick(position: Int)
    }

}