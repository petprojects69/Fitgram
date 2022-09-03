package ru.petprojects69.fitgram.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.petprojects69.fitgram.domain.TypeConverterExerciseList

@Entity(tableName = "training")
@TypeConverters(TypeConverterExerciseList::class)
class TrainingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val label: String?,
    val exerciseList: MutableList<TrainingExercise>?
)
