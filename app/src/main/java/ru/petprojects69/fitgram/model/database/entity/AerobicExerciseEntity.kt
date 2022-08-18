package ru.petprojects69.fitgram.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AerobicExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id") val id: Int,
    @Embedded val exercise: ExerciseEntity,
    @ColumnInfo(name = "lead_time")val leadTime: Float = 0.0f,
)