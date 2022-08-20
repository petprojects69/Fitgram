package ru.petprojects69.fitgram.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Exercise"
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id") val id: Int,
    @ColumnInfo(name = "exercise_name") val name: String? = null,
    @ColumnInfo(name = "exercise_description") val description: String? = null,
    @ColumnInfo(name = "exercise_poster") val poster: String? = null,
    @ColumnInfo(name = "exercise_difficulty") val difficulty: String? = null,
    @ColumnInfo(name = "exercise_location") val location: String? = null,
    @ColumnInfo(name = "exercise_burningCalories") val burningCalories: Int? = null,
    @ColumnInfo(name = "exercise_cooldown") val cooldown: Float? = null,
    @ColumnInfo(name = "exercise_muscleGroup") val muscleGroup: Float? = null,
)
