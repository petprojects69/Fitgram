package ru.petprojects69.fitgram.data.entity

import ru.petprojects69.fitgram.domain.entity.UserEntity

fun UserEntityRemote.toMap(): Map<String, Any?> {
    return mapOf(
        "email" to this.email,
        "sex" to this.sex,
        "name" to this.name,
        "surname" to this.surname,
        "rank" to this.rank,
        "age" to this.age,
        "weight" to this.weight,
        "height" to this.height,
        "calories" to this.calories,
        "completed" to this.completed
    )
}

fun UserEntity.toUserEntityRemote(): UserEntityRemote {
    return UserEntityRemote(
        age = this.age,
        calories = this.calories,
        completed = this.completed,
        email = this.email,
        height = this.height,
        id = this.id,
        name = this.name,
        rank = this.rank,
        sex = this.sex,
        surname = this.surname,
        weight = this.weight
    )
}