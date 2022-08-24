package ru.petprojects69.fitgram.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ru.petprojects69.fitgram.domain.TypeConverterExerciseList

open class BasicExercise(
    open val label: String? = null,
    open val calories: Int? = null,
    open val runTime: Int? = null
)

class AerobicEx(
    labelAerobic: String? = null,
    caloriesAerobic: Int? = null,
    runTimeAerobic: Int? = null,
    val type: AerobicType? = null
) : BasicExercise(labelAerobic, caloriesAerobic, runTimeAerobic)

enum class AerobicType {
    FREE, RUN, YOGA, PILATES
}

class PowerEx(
    labelPower: String? = null,
    caloriesPower: Int? = null,
    runTimePower: Int? = null,
    val count: Int? = null,
    val iteration: Int? = null
) : BasicExercise(labelPower, caloriesPower, runTimePower)

@Entity(tableName = "training")
@TypeConverters(TypeConverterExerciseList::class)
class Training(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val label: String?,
    val exerciseList: MutableList<BasicExercise>?
)