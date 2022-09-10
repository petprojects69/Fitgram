package ru.petprojects69.fitgram.ui.exercisesFragment

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity

interface OnItemExerciseClickListener {
    fun onItemExerciseClick(exercise: AerobicExerciseEntity)
}