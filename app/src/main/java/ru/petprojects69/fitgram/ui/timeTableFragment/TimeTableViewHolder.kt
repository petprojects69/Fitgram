package ru.petprojects69.fitgram.ui.timeTableFragment

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemTimetableBinding
import ru.petprojects69.fitgram.domain.entity.DatedTrainingEntity
import ru.petprojects69.fitgram.ui.innerAdapter.InnerTimeTableAdapter
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat

class TimeTableViewHolder(private val binding: ItemTimetableBinding) :
    RecyclerView.ViewHolder(binding.root) {
    private val view = WeakReference(binding.root)

    var onChangeClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var onDeleteClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var detailsClick: ((RecyclerView.ViewHolder) -> Unit)? = null
    var onItemClick: ((RecyclerView.ViewHolder) -> Unit)? = null

    private val innerAdapter = InnerTimeTableAdapter()

    init {
        view.get()?.let {
            it.setOnClickListener {
                onItemClick?.let { onItemClick ->
                    onItemClick(this)
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

    @SuppressLint("UseCompatLoadingForDrawables", "SimpleDateFormat")
    fun bind(datedTraining: Pair<DatedTrainingEntity, Boolean>) {

        if (datedTraining.second) {
            binding.detailsRecyclerView.visibility = View.VISIBLE
            binding.detailsBtnImageView.setImageDrawable(
                binding.root.context.getDrawable(R.drawable.ic_baseline_expand_less_24)
            )

            datedTraining.first.training.exerciseList?.let {
                innerAdapter.initData(it)
            }
        } else {
            binding.detailsRecyclerView.visibility = View.GONE
            binding.detailsBtnImageView.setImageDrawable(
                binding.root.context.getDrawable(R.drawable.ic_baseline_expand_more_24)
            )
        }
        binding.titleTextView.text = datedTraining.first.training.label.toString()
        binding.dataTextView.text = SimpleDateFormat("dd.MM.yyyy").format(datedTraining.first.date)

        setBackgroundColor(datedTraining.first.date)
    }

    private fun setBackgroundColor(trainingDate: Long) {
        val context = binding.root.context
        val currentDate = Calendar.getInstance().let {
            it.set(Calendar.HOUR_OF_DAY, 0)
            it.set(Calendar.MINUTE, 0)
            it.set(Calendar.SECOND, 0)
            it.set(Calendar.MILLISECOND, 0)
            it.timeInMillis
        }
        val containerViews = listOf(
            binding.itemTimetable,
            binding.trainingContainer,
        )
        val textViews = listOf(
            binding.dataTextView,
            binding.titleTextView,
            binding.timeTextView
        )

        when {
            trainingDate < currentDate -> {
                containerViews.forEach { it.setBackgroundColor(context.getColor(R.color.past_training_background_color)) }
                textViews.forEach { it.setTextColor(context.getColor(R.color.past_training_text_color)) }
            }
            trainingDate == currentDate -> {
                containerViews.forEach {
                    it.setBackgroundColor(context.getColor(R.color.present_training_background_color))
                    textViews.forEach { it.setTextColor(context.getColor(R.color.text_color)) }
                }
            }
            trainingDate > currentDate -> {
                containerViews.forEach { it.setBackgroundColor(context.getColor(R.color.white)) }
                textViews.forEach { it.setTextColor(context.getColor(R.color.text_color)) }
            }
        }
    }

    private fun initInnerRecyclerView() {
        binding.detailsRecyclerView.adapter = innerAdapter
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
    }
}