package ru.petprojects69.fitgram.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PowerExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "powerExercise") val id: Int,
    @Embedded val exercise: ExerciseEntity,
    val numberOfRepetitions: Int = 0,
)