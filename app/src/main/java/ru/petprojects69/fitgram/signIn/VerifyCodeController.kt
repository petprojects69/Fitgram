package ru.petprojects69.fitgram.signIn

interface VerifyCodeController {
    fun verifyPhoneNumberWithCode(code: String)
    fun resendVerificationCode(phoneNumber: String)
}