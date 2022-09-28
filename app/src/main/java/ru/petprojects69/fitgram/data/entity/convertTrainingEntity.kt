package ru.petprojects69.fitgram.data.entity

import ru.petprojects69.fitgram.domain.entity.base.Training

fun Training.toTrainingRemote() =
    TrainingRemote(
        id = this.id,
        label = this.label,
        exerciseList = this.exerciseList
    )