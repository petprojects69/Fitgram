package ru.petprojects69.fitgram.domain.entity.exercise

sealed class ExerciseList{
    class PowerExercise (val exercise: PowerExerciseEntity) : ExerciseList()
    class AerobicExercise (val exercise: AerobicExerciseEntity) : ExerciseList()
}
