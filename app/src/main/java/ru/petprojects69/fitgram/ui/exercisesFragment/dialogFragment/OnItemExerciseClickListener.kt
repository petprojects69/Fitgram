package ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity

interface OnItemExerciseClickListener {
    fun onItemExerciseClick(exercise: AerobicExerciseEntity)
    fun onItemExerciseClick(exercise: PowerExerciseEntity)
}