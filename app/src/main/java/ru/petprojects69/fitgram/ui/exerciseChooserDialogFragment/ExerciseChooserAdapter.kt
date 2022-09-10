package ru.petprojects69.fitgram.ui.exerciseChooserDialogFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemExerciseChooserBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

class ExerciseChooserAdapter : RecyclerView.Adapter<ExerciseChooserViewHolder>() {

    var exNameList: MutableList<ExerciseEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseChooserViewHolder {
        val binding = ItemExerciseChooserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseChooserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseChooserViewHolder, position: Int) {
        holder.bind(exNameList[position])
    }

    override fun getItemCount(): Int {
        return exNameList.size
    }
}