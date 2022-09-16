package ru.petprojects69.fitgram.ui.trainingConstructorDialogFragment

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ItemTrainingConstructorBinding
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseType

class TrainingConstructorViewHolder(private val binding: ItemTrainingConstructorBinding) :
    RecyclerView.ViewHolder(binding.root) {

    val context: Context = binding.root.context

    fun bind(exCustomized: ExerciseCustomized) {

        binding.exerciseTitleTextView.apply {
            text = exCustomized.exInitial.name
            ellipsize = TextUtils.TruncateAt.MARQUEE
            isSelected = true
        }

        when (exCustomized.exInitial.type) {
            ExerciseType.AEROBIC -> {
                binding.itemExerciseBaseCardView.strokeColor =
                    context.getColor(R.color.item_aerobic_exercise_stroke_color)
                binding.exerciseSetsEditText.visibility = GONE
                binding.charBetweenSetsAndRepsTextView.visibility = GONE
                binding.exerciseRepsOrDurationEditText.hint = context.getString(R.string.exercise_constructor_duration_hint)

                val saveDurationCallback: (Int?) -> Unit = {
                    exCustomized.duration = it
                }
                readFromEditText(saveDurationCallback, binding.exerciseRepsOrDurationEditText)
            }

            ExerciseType.POWER -> {
                binding.itemExerciseBaseCardView.strokeColor =
                    context.getColor(R.color.item_power_exercise_stroke_color)
                binding.exerciseSetsEditText.apply {
                    visibility = VISIBLE
                    hint = context.getString(R.string.exercise_constructor_sets_hint)
                }
                binding.charBetweenSetsAndRepsTextView.apply {
                    visibility = VISIBLE
                    text = context.getString(R.string.char_between_sets_and_reps_textView)
                }
                binding.exerciseRepsOrDurationEditText.hint = context.getString(R.string.exercise_constructor_reps_hint)

                val saveSetsCallback: (Int?) -> Unit = {
                    exCustomized.sets = it
                }
                readFromEditText(saveSetsCallback, binding.exerciseSetsEditText)

                val saveRepsCallback: (Int?) -> Unit = {
                    exCustomized.reps = it
                }
                readFromEditText(saveRepsCallback, binding.exerciseRepsOrDurationEditText)

            }
        }
    }

    private fun readFromEditText(callback: (Int?) -> Unit, et: EditText) {

        et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                callback.invoke(et.text.toString().toIntOrNull())
            }
        })
    }
}
