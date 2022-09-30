package ru.petprojects69.fitgram.signIn

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.android.ext.android.inject
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ActivitySigninFirebaseBinding
import ru.petprojects69.fitgram.ui.MainActivity
import ru.petprojects69.fitgram.ui.MainActivity.Companion.PREF_USER_ID_KEY
import ru.petprojects69.fitgram.ui.utils.showSnack
import java.util.concurrent.TimeUnit

class SignInFirebaseActivity : AppCompatActivity(), VerifyCodeController,
    StartSignInController {

    private val binding: ActivitySigninFirebaseBinding by viewBinding()
    private lateinit var auth: FirebaseAuth
    private lateinit var signInClient: SignInClient

    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var phoneNumber: String
    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            handleSignInResult(result.data)
        }
    private val editor: SharedPreferences.Editor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_signin_firebase)
        decorStatusBar()

        supportFragmentManager.beginTransaction()
            .add(binding.signInContainer.id, StartSignInFragment())
            .commit()

        signInClient = Identity.getSignInClient(this)

        auth = Firebase.auth

        val currentUser = auth.currentUser
        if (currentUser == null) {
            oneTapSignIn()
        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                verificationInProgress = false
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                verificationInProgress = false
                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        binding.root.showSnack("Номер не существует")
                    }
                    is FirebaseTooManyRequestsException -> {
                        binding.root.showSnack("Превышена квота")
                    }
                    is FirebaseAuthException -> {
                        Log.d(TAG, e.message.toString())
                        binding.root.showSnack("Ошибка авторизации. Используйте другой способ")
                    }
                    is FirebaseApiNotAvailableException -> {
                        binding.root.showSnack("Отсутствуют Google Play Services")
                    }
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken,
            ) {
                storedVerificationId = verificationId
                resendToken = token
                startCheckingCodeFragment(phoneNumber)
            }
        }
    }

    private fun oneTapSignIn() {
        val oneTapRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.your_web_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build()
            )
            .build()

        signInClient.beginSignIn(oneTapRequest)
            .addOnSuccessListener { result ->
                launchSignIn(result.pendingIntent)
            }
    }

    private fun launchSignIn(pendingIntent: PendingIntent) {
        try {
            val intentSenderRequest = IntentSenderRequest.Builder(pendingIntent)
                .build()
            signInLauncher.launch(intentSenderRequest)
        } catch (e: IntentSender.SendIntentException) {
            Log.e(TAG, "Couldn't start Sign In: ${e.localizedMessage}")
        }
    }

    override fun signIn() {
        val signInRequest = GetSignInIntentRequest.builder()
            .setServerClientId(getString(R.string.your_web_client_id))
            .build()

        signInClient.getSignInIntent(signInRequest)
            .addOnSuccessListener { pendingIntent ->
                launchSignIn(pendingIntent)
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Google Sign-in failed", e)
            }
    }

    private fun handleSignInResult(data: Intent?) {
        try {
            val credential = signInClient.getSignInCredentialFromIntent(data)
            val idToken = credential.googleIdToken
            if (idToken != null) {
                firebaseAuthWithGoogle(idToken)
            }
        } catch (e: ApiException) {
            Log.w(TAG, "Google sign in failed", e)
            updateUI(null)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    binding.root.showSnack("Ошибка авторизации")
                }
            }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
        if (verificationInProgress) {
            startPhoneNumberVerification(phoneNumber)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startMainActivityWithID(user.uid)
            finish()
        }
    }

    override fun startPhoneNumberVerification(phoneNumber: String) {
        this.phoneNumber = phoneNumber
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        verificationInProgress = true
    }

    override fun verifyPhoneNumberWithCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    override fun resendVerificationCode(phoneNumber: String) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
        optionsBuilder.setForceResendingToken(resendToken)
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        binding.root.showSnack("Неверный код")
                    }
                }
            }
    }

    private fun decorStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = this.resources.getColor(R.color.color_for_status_bar, null)
    }

    // метод для потери фокуса при клике вне поля ввода
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is TextInputEditText || v is MaterialAutoCompleteTextView) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    v.clearFocus()
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(v.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun startCheckingCodeFragment(phoneNumber: String) {
        val bundle = Bundle()
        bundle.putString(
            CheckingCodeFragment.PHONE_NUMBER_KEY,
            phoneNumber
        )

        supportFragmentManager.beginTransaction()
            .replace(binding.root.id, CheckingCodeFragment.newInstance(bundle))
            .addToBackStack("")
            .commit()
    }

    private fun startMainActivityWithID(uid: String?) {
        saveUserIdWithPreferences(uid)
        val startIntent = Intent(this, MainActivity::class.java)
        startActivity(startIntent)
    }

    private fun saveUserIdWithPreferences(uid: String?) {
        editor.putString(PREF_USER_ID_KEY, uid).commit()
    }

    override fun loginAnonymous() {
        val startIntent = Intent(this, MainActivity::class.java)
        startActivity(startIntent)
    }

    companion object {
        private const val TAG = "SignInFirebaseActivity"
    }
}