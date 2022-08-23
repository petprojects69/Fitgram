package ru.petprojects69.fitgram.domain.entity.exercise

import androidx.room.*

@Entity(tableName = "power_exercise_table")
data class PowerExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "power_exercise_id") val idPowerExercise: Int = 0,
    @Embedded val exerciseBase: ExerciseEntity,
    @ColumnInfo(name = "number_repetitions") val numberOfRepetitions: Int = 0,
)