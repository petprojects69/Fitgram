package ru.petprojects69.fitgram.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: String,
    val email: String = "null",
    val sex: Boolean = true,
    val name: String? = null,
    val surname: String? = null,
    val rank: Int? = null,
    val age: Int? = null,
    val weight: Int? = null,
    val height: Int? = null,
    val target: String? = null,
    val calories: Int? = null,
    val completed: Boolean = false,
)