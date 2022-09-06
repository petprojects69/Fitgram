package ru.petprojects69.fitgram.ui.innerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.InnerItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized

class InnerTimeTableAdapter : RecyclerView.Adapter<InnerTimeTableViewHolder>() {
    private val exerciseList: MutableList<ExerciseCustomized> = mutableListOf()

    fun initData(list: MutableList<ExerciseCustomized>) {
        exerciseList.clear()
        for (item in list) {
            exerciseList.add(exerciseList.size, item)
            notifyItemInserted(exerciseList.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerTimeTableViewHolder {
        val binding = InnerItemTrainingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InnerTimeTableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InnerTimeTableViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int = exerciseList.size
}