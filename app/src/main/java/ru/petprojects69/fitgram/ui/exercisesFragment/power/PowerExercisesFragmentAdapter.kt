package ru.petprojects69.fitgram.ui.exercisesFragment.power

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemPowerExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment.OnItemExerciseClickListener

class PowerExercisesFragmentAdapter(private val itemClickListener: OnItemExerciseClickListener) :
    RecyclerView.Adapter<PowerExercisesFragmentViewHolder>() {

    var exercisePowerList: MutableList<ExerciseEntity> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PowerExercisesFragmentViewHolder {
        val binding = ItemPowerExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PowerExercisesFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PowerExercisesFragmentViewHolder, position: Int) {
        holder.bind(exercisePowerList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return exercisePowerList.size
    }


}