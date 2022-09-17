package ru.petprojects69.fitgram.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized

class TypeConverterExerciseList {

    @TypeConverter
    fun listToJson(value: List<ExerciseCustomized>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<ExerciseCustomized>::class.java).toMutableList()
}