package ru.petprojects69.fitgram.domain.entity.exercisesEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.petprojects69.fitgram.R

@Entity(tableName = "exercise_table")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exercise_id") val id: Int = 0,
    @ColumnInfo(name = "type") val type: ExerciseType = ExerciseType.POWER,
    @ColumnInfo(name = "exercise_name") val name: String = "Common name",
    @ColumnInfo(name = "exercise_description") val description: String,
    @ColumnInfo(name = "exercise_poster") val poster: Int = R.drawable.poster_temp_exercise,
    @ColumnInfo(name = "exercise_difficulty") val difficulty: String? = null,
    @ColumnInfo(name = "exercise_location") val location: String? = null,
    @ColumnInfo(name = "exercise_burningCalories") val burningCalories: Int? = null,
    @ColumnInfo(name = "exercise_cooldown") val cooldown: Float? = null,
    @ColumnInfo(name = "exercise_muscleGroup") val muscleGroup: String? = null,
    @ColumnInfo(name = "exercise_posterCustom") val posterCustom: String? = null,
)

enum class ExerciseType {
    AEROBIC,
    POWER
}