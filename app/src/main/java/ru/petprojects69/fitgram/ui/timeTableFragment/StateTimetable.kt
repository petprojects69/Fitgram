package ru.petprojects69.fitgram.ui.timeTableFragment

import ru.petprojects69.fitgram.domain.entity.TrainingEntity

sealed class StateTimetable{
    object Loading: StateTimetable()
    object Empty: StateTimetable()

    data class Success(val exerciseList: MutableList<TrainingEntity>): StateTimetable()
    data class Error(val e: Throwable): StateTimetable()
}
