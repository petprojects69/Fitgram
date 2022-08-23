package ru.petprojects69.fitgram.domain.entity.exercise

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aerobic_exercise_table")
data class AerobicExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "aerobic_exercise_id") val idAerobicExercise: Int = 0,
    @Embedded val exerciseBase: ExerciseEntity,
    @ColumnInfo(name = "lead_time") val leadTime: Float = 0.0f,
)

