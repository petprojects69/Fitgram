package ru.petprojects69.fitgram.data.entity

import ru.petprojects69.fitgram.domain.entity.ExerciseCustomized
import ru.petprojects69.fitgram.domain.entity.base.Training

data class TrainingRemote(
    val typeEntity: String = TRAINING_ENTITY_KEY,
    override val id: String,
    override val label: String?,
    override val exerciseList: MutableList<ExerciseCustomized>?
) : Training(id, label, exerciseList) {
    companion object {
        const val TRAINING_ENTITY_KEY = "trainingEntityKey"
    }
}