package ru.petprojects69.fitgram.ui.timeTableFragment

import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTimetableBinding
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import java.lang.ref.WeakReference

class TimeTableHolder(private val binding: ItemTimetableBinding) :
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

    fun bind(exercise: PowerExerciseEntity) {
        binding.titleTextView.text = exercise.exercise.name
        binding.timeTextView.text = exercise.numberOfRepetitions.toString()
    }
}