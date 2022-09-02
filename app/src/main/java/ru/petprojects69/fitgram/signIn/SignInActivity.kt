package ru.petprojects69.fitgram.signIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ActivitySignInBinding
import ru.petprojects69.fitgram.ui.MainActivity

private const val RC_SIGN_IN = 9001

class SignInActivity : AppCompatActivity() {
    private val binding: ActivitySignInBinding by viewBinding()
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val gso: GoogleSignInOptions =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.signInGoogleButton.setOnClickListener {
            signIn()
        }
        binding.loginWithoutAuthorizationTextView.setOnClickListener {
            loginWithAuthorisation()
        }
        binding.phoneBtn.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, PhoneSignInFragment())
                .commit()
        }
    }

    private fun loginWithAuthorisation() {
        val startIntent = Intent(this, MainActivity::class.java)
        startActivity(startIntent)
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            updateUI(account)
        } catch (e: ApiException) {
            Log.w("TAG", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account !== null) {
            val startIntent = Intent(this, MainActivity::class.java)
            startActivity(startIntent)
            Toast.makeText(
                this, "Добро пожаловать, ${account.displayName}!", Toast.LENGTH_SHORT
            ).show()
        }
    }
}