package ru.petprojects69.fitgram.domain.entity.exercisesEntity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aerobic_exercise_table")
data class AerobicExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "aerobic_exercise_id") val id: Int = 0,
    @ColumnInfo(name = "exercise_name") val name: String? = null,
    @ColumnInfo(name = "exercise_description") val description: String? = null,
    @ColumnInfo(name = "exercise_poster") val poster: Int? = null,
    @ColumnInfo(name = "exercise_difficulty") val difficulty: String? = null,
    @ColumnInfo(name = "exercise_location") val location: String? = null,
    @ColumnInfo(name = "exercise_burningCalories") val burningCalories: Int? = null,
    @ColumnInfo(name = "exercise_cooldown") val cooldown: Float? = null,
    @ColumnInfo(name = "exercise_muscleGroup") val muscleGroup: String? = null,
)