package ru.petprojects69.fitgram.domain.useCase

import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.petprojects69.fitgram.ui.MainActivity.Companion.PREF_USER_ID_KEY

class SignOutUseCase: KoinComponent {
    private val editor: SharedPreferences.Editor by inject()
    fun execute(auth: FirebaseAuth) {
        auth.signOut()
        editor.putString(PREF_USER_ID_KEY, null).commit()
    }
}