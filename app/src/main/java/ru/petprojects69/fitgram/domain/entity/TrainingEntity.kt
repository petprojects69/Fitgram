package ru.petprojects69.fitgram.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.petprojects69.fitgram.data.database.TypeConverterExerciseList
import java.io.Serializable

@Entity(tableName = "training")
@TypeConverters(TypeConverterExerciseList::class)
class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val label: String?,
    val exerciseList: MutableList<ExerciseCustomized>?,
) : Serializable
