package ru.petprojects69.fitgram.domain.entity.exercisesEntity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "power_exercise_table")
data class PowerExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "power_exercise_id") val id: Int = 0,
    @Embedded val exercise: ExerciseEntity,
)