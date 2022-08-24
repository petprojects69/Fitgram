package ru.petprojects69.fitgram.ui.timeTableFragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemTimetableBinding
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.ui.innerAdapter.InnerTimeTableAdapter
import java.lang.ref.WeakReference

class TimeTableHolder(private val binding: ItemTimetableBinding, private val context: Context) :
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

    // TODO innerAdapter.initData()
    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(exercise: Pair<Training, Boolean>) {

        if (exercise.second) {
            binding.detailsRecyclerView.visibility = View.VISIBLE
            binding.detailsBtnImageView.setImageDrawable(
                context.getDrawable(R.drawable.ic_baseline_expand_less_24)
            )
            exercise.first.exerciseList?.let {
                val k = it
                innerAdapter.initData(it)
            }
        } else {
            binding.detailsRecyclerView.visibility = View.GONE
            binding.detailsBtnImageView.setImageDrawable(
                context.getDrawable(R.drawable.ic_baseline_expand_more_24)
            )
        }
        binding.titleTextView.text = exercise.first.label.toString()
    }

    private fun initInnerRecyclerView() {
        binding.detailsRecyclerView.adapter = innerAdapter
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(context)
    }
}