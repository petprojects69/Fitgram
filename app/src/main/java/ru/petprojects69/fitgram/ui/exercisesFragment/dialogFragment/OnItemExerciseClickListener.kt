package ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity

interface OnItemExerciseClickListener {
    fun onItemExerciseClick(exercise: ExerciseEntity)
}