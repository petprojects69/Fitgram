package ru.petprojects69.fitgram.signIn

interface StartSignInController {
    fun signIn()
    fun startPhoneNumberVerification(phoneNumber: String)
    fun loginAnonymous()
}