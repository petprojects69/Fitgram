package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.DialogTrainingConstructorBinding
import ru.petprojects69.fitgram.databinding.ItemTrainingConstructorBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class TrainingConstructorAdapter :
    RecyclerView.Adapter<TrainingConstructorViewHolder>() {

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
        return TrainingConstructorViewHolder(binding)
    }

    fun addExCustom(binding: DialogTrainingConstructorBinding, exercise: ExerciseEntity) {
        val position = exCustomList.size

        exCustomList.add(ExerciseCustomized(exercise))
        binding.recyclerView.scrollToPosition(position)
        notifyItemInserted(position)
    }

    override fun onBindViewHolder(holder: TrainingConstructorViewHolder, position: Int) {
        holder.bind(exCustomList[position])
    }

    override fun getItemCount(): Int {
        return exCustomList.size
    }

}