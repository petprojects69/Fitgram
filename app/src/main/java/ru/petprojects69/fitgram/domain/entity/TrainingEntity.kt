package ru.petprojects69.fitgram.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.petprojects69.fitgram.data.database.TypeConverterExerciseList
import ru.petprojects69.fitgram.domain.entity.base.Training
import ru.petprojects69.fitgram.domain.generateId
import java.io.Serializable

@Entity(tableName = "training")
@TypeConverters(TypeConverterExerciseList::class)
class TrainingEntity(
    @PrimaryKey
    override val id: String = generateId(),
    override val label: String?,
    override val exerciseList: MutableList<ExerciseCustomized>?,
) : Training(id, label, exerciseList), Serializable