package ru.petprojects69.fitgram.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.petprojects69.fitgram.domain.entity.base.Training
import ru.petprojects69.fitgram.domain.generateId

@Entity(tableName = "dated_training")
data class DatedTrainingEntity(
    @PrimaryKey
    @ColumnInfo(name = "datedTrainingId") val datedTrainingId: String = generateId(),
    @Embedded val training: TrainingEntity,
    @ColumnInfo(name = "date") val date: Long
)