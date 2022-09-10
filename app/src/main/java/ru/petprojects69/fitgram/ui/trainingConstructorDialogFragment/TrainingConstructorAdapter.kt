package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTrainingConstructorBinding
import ru.petprojects69.fitgram.databinding.TrainingConstructorDialogBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized

class TrainingConstructorAdapter :
    RecyclerView.Adapter<TrainingConstructorRecyclerViewHolder>() {

    var exCustomList: MutableList<ExerciseCustomized> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainingConstructorRecyclerViewHolder {

        val binding = ItemTrainingConstructorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingConstructorRecyclerViewHolder(binding)

    }

    fun addExCustom(binding: TrainingConstructorDialogBinding) {
        val position = exCustomList.size
        exCustomList.add(position, ExerciseCustomized(null,null,null,null,null))

        binding.recyclerView.scrollToPosition(position)
        notifyItemInserted(position)
    }

    override fun onBindViewHolder(holder: TrainingConstructorRecyclerViewHolder, position: Int) {
        holder.bind(exCustomList[position])
    }

    override fun getItemCount(): Int {
        return exCustomList.size
    }

}