package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemPowerExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

class TrainingConstructorAdapter :
    RecyclerView.Adapter<TrainingConstructorRecyclerViewHolder>() {

    var exercisePowerList: MutableList<PowerExerciseEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainingConstructorRecyclerViewHolder {

        val binding = ItemPowerExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingConstructorRecyclerViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TrainingConstructorRecyclerViewHolder, position: Int) {
        holder.bind(exercisePowerList[position])
    }

    override fun getItemCount(): Int {
        return exercisePowerList.size
    }

}