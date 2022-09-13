package ru.petprojects69.fitgram.data

data class FakeUser(
    val email: String = "sh@mail.com",
    val name: String = "Арнольд",
    val surname: String = "Шварценеггер",
    val rank: String = "Терминатор",
    val gender: String = "Мужской",
    val age: Int = 3000,
    val weight: Float = 180f,
    val height: Float = 188f,
    val calories: Int = 55000,
    val completed: Boolean = true,
)