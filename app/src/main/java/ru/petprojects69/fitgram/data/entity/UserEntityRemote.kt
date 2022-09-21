package ru.petprojects69.fitgram.data.entity

import ru.petprojects69.fitgram.domain.entity.base.User

data class UserEntityRemote(
    val typeEntity: String = USER_DATA_ENTITY_KEY,
    override val id: String,
    override val email: String,
    override val sex: String,
    override val name: String?,
    override val surname: String?,
    override val rank: Int?,
    override val age: Int?,
    override val weight: Float?,
    override val height: Int?,
    override val target: String?,
    override val calories: Int?,
    override val completed: Boolean
) : User(id, email, sex, name, surname, rank, age, weight, height, target, calories, completed) {
    companion object {
        const val USER_DATA_ENTITY_KEY = "userDataEntityKey"
    }
}