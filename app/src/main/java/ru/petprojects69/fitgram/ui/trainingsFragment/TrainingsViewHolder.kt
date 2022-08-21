package ru.petprojects69.fitgram.ui.trainingsFragment

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity

class TrainingsViewHolder(private val binding: ItemTrainingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    // TODO изменить ExerciseEntity на Тренировка
    fun bind(training: PowerExerciseEntity) {
        binding.titleTextView.text = training.exercise.name.toString()
    }
}