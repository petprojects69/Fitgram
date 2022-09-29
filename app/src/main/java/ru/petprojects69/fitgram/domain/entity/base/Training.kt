package ru.petprojects69.fitgram.domain.entity.base

import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized

open class Training(
    open val id: String,
    open val label: String?,
    open val exerciseList: MutableList<ExerciseCustomized>?,
)