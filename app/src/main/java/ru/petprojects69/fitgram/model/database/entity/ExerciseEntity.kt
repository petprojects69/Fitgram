package ru.petprojects69.fitgram.model.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Exercise",
    indices = [Index("workoutId")]
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id") val id: Int,
    @ColumnInfo(name = "workout_name") val name: String? = null,
    @ColumnInfo(name = "workout_description") val description: String? = null,
    @ColumnInfo(name = "workout_avatar") val avatar: String? = null,
    @ColumnInfo(name = "workout_difficulty") val difficulty: String? = null,
    @ColumnInfo(name = "workout_location") val location: String? = null,
    @ColumnInfo(name = "workout_burningCalories") val burningCalories: Int? = null,
    @ColumnInfo(name = "workout_cooldown") val cooldown: Float? = null,
    @ColumnInfo(name = "workout_muscleGroup") val muscleGroup: Float? = null,
)
