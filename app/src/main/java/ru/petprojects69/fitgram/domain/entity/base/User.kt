package ru.petprojects69.fitgram.domain.entity.base

open class User(
    open val id: String,
    open val email: String = "null",
    open val sex: Boolean = true,
    open val name: String? = null,
    open val surname: String? = null,
    open val rank: Int? = null,
    open val age: Int? = null,
    open val weight: Float? = null,
    open val height: Int? = null,
    open val target: String? = null,
    open val calories: Int? = null,
    open val completed: Boolean = false
)