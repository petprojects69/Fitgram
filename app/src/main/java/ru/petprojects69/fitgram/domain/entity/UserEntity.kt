package ru.petprojects69.fitgram.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val email: String = "null",
    val name: String? = null,
    val surname: String? = null,
    val age: Int? = null,
    val weight: Float? = null,
    val height: Float? = null,
    val completed: Boolean = false,
)