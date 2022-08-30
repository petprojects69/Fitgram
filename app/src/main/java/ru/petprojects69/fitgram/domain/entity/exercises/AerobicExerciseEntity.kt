package ru.petprojects69.fitgram.domain.entity.exercises

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aerobic_exercise_table")
data class AerobicExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "aerobic_exercise_id") val id: Int = 0,
    @Embedded val exercise: ExerciseEntity,
)