package ru.petprojects69.fitgram.domain

import androidx.room.TypeConverter
import com.google.gson.Gson
import ru.petprojects69.fitgram.domain.entity.TrainingExercise

class TypeConverterExerciseList {

    @TypeConverter
    fun listToJson(value: List<TrainingExercise>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String) =
        Gson().fromJson(value, Array<TrainingExercise>::class.java).toMutableList()
}