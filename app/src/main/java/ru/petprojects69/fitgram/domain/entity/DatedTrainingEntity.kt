package ru.petprojects69.fitgram.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dated_training")
data class DatedTrainingEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "number") val number: Int = 0,
    @Embedded val training: TrainingEntity,
    @ColumnInfo(name = "date") val date: Long
)