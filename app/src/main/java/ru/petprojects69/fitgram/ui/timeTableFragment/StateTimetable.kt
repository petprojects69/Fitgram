package ru.petprojects69.fitgram.ui.timeTableFragment

import ru.petprojects69.fitgram.domain.entity.ExerciseEntity

sealed class StateTimetable{
    object Loading: StateTimetable()
    object Empty: StateTimetable()
    // TODO изменить тип списка с ExerciseEntity на Тренировки, когда появится в БД
    data class Success(val exerciseList: MutableList<ExerciseEntity>): StateTimetable()
    data class Error(val e: Throwable): StateTimetable()
}
