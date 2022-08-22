package ru.petprojects69.fitgram.ui.trainingsFragment

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import java.lang.ref.WeakReference

class TrainingsViewHolder(private val binding: ItemTrainingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val view = WeakReference(binding.root)

    var onChangeClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var onDeleteClick: ((RecyclerView.ViewHolder) -> Unit)? = null

    init {
        view.get()?.let {
            it.setOnClickListener {
                if (view.get()?.scrollX != 0) {
                    view.get()?.scrollTo(0, 0)
                }
            }

            binding.btnChange.setOnClickListener {
                onChangeClick?.let { onChangeClick ->
                    onChangeClick(this)
                }
                view.get()?.scrollTo(0, 0)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick?.let { onDeleteClick ->
                    onDeleteClick(this)
                }
                view.get()?.scrollTo(0, 0)
            }
        }
    }

    // TODO изменить ExerciseEntity на Тренировка
    fun bind(training: PowerExerciseEntity) {
        binding.titleTextView.text = training.exercise.name.toString()
    }
}
