package ru.petprojects69.fitgram.ui.timeTableFragment

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.databinding.ItemTimetableBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.PowerExerciseEntity
import ru.petprojects69.fitgram.ui.timeTableFragment.innerAdapter.InnerTimeTableAdapter
import java.lang.ref.WeakReference

class TimeTableHolder(private val binding: ItemTimetableBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    private val view = WeakReference(binding.root)

    var onChangeClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var onDeleteClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var detailsClick: ((RecyclerView.ViewHolder) -> Unit)? = null

    private val innerAdapter = InnerTimeTableAdapter()

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
            binding.detailsBtnImageView.setOnClickListener {
                detailsClick?.let { detailsClick ->
                    detailsClick(this)
                }
            }
            initInnerRecyclerView()
        }
    }

    // TODO innerAdapter.initData()
    fun bind(exercise: Pair<PowerExerciseEntity, Boolean>) {
        binding.detailsRecyclerView.visibility = if (exercise.second) View.VISIBLE else View.GONE
        binding.titleTextView.text = exercise.first.exercise.name
        binding.timeTextView.text = exercise.first.numberOfRepetitions.toString()
        innerAdapter.initData(
            // Test data
            mutableListOf(
                ExerciseEntity(0, "Упражнение 1", null, null, null, null, null, null, null),
                ExerciseEntity(0, "Упражнение 2", null, null, null, null, null, null, null),
                ExerciseEntity(0, "Упражнение 3", null, null, null, null, null, null, null),
            )
        )
    }

    private fun initInnerRecyclerView() {
        binding.detailsRecyclerView.adapter = innerAdapter
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}