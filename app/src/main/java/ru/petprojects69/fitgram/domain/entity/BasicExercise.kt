package ru.petprojects69.fitgram.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.petprojects69.fitgram.domain.TypeConverterExerciseList
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

open class BasicExercise(
    val name: String?,
)

data class AerobicEx(
    val exercise: AerobicExerciseEntity,
    val duration: Int?
) : BasicExercise(name = exercise.exercise.name)

data class PowerEx(
    val exercise: PowerExerciseEntity,
    val sets: Int,
    val reps: Int
) : BasicExercise(name = exercise.exercise.name)


@Entity(tableName = "training")
@TypeConverters(TypeConverterExerciseList::class)
class Training(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val label: String?,
    val exerciseList: MutableList<BasicExercise>?
)