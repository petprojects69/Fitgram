package ru.petprojects69.fitgram.ui.exercisesFragment.aerobic

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemAerobicExerciseBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment.OnItemExerciseClickListener

class AerobicExercisesFragmentAdapter(private val itemClickListener: OnItemExerciseClickListener) :
    RecyclerView.Adapter<AerobicExercisesFragmentViewHolder>() {

    var exerciseAerobicList: MutableList<ExerciseEntity> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AerobicExercisesFragmentViewHolder {
        val binding = ItemAerobicExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AerobicExercisesFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AerobicExercisesFragmentViewHolder, position: Int) {
        holder.bind(exerciseAerobicList[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return exerciseAerobicList.size
    }
}