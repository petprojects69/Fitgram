package ru.petprojects69.fitgram.ui.trainingsFragment

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemTrainingBinding
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.ui.innerAdapter.InnerTimeTableAdapter
import java.lang.ref.WeakReference

class TrainingsViewHolder(private val binding: ItemTrainingBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val view = WeakReference(binding.root)
    private val innerAdapter = InnerTimeTableAdapter()

    var onChangeClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var onDeleteClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var detailsClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var itemClick: ((RecyclerView.ViewHolder) -> Unit)? = null

    init {
        view.get()?.let {
            it.setOnClickListener {
                itemClick?.let { itemClick ->
                    itemClick(this)
                }
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

    // TODO изменить ExerciseEntity на Тренировка
    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(training: Pair<TrainingEntity, Boolean>) {
        if (training.second) {
            binding.detailsRecyclerView.visibility = View.VISIBLE
            binding.detailsBtnImageView.setImageDrawable(
                binding.root.context.getDrawable(R.drawable.ic_baseline_expand_less_24)
            )
        } else {
            binding.detailsRecyclerView.visibility = View.GONE
            binding.detailsBtnImageView.setImageDrawable(
                binding.root.context.getDrawable(R.drawable.ic_baseline_expand_more_24)
            )
        }
        binding.titleTextView.text = training.first.label.toString()

        training.first.exerciseList?.let {
            innerAdapter.initData(it)
        }
    }

    private fun initInnerRecyclerView() {
        binding.detailsRecyclerView.adapter = innerAdapter
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    }
}

