package ru.petprojects69.fitgram.ui

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.android.inject
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.data.database.AppDatabaseDao
import ru.petprojects69.fitgram.databinding.ActivityMainBinding
import ru.petprojects69.fitgram.ui.initData.InitialDataFragment
import ru.petprojects69.fitgram.ui.initData.MainActivityController
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity(), MainActivityController {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private var isFilledUserData by Delegates.notNull<Boolean>()
    private var userId: String? = ""
    private val preferences: SharedPreferences by inject()
    private val editor: SharedPreferences.Editor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)
        decorStatusBar()
        userId = preferences.getString(PREF_USER_ID_KEY, null)
        isFilledUserData = preferences.getBoolean(PREF_IS_FILLED_USER_DATA, false)
        checkingFirstLaunch()
        startFragment()
        viewModel.getTrainingInLocal().observe(this) {
            viewModel.presetInRemoteStorage(it)
        }
    }

    private fun startFragment() {
        if (!isFilledUserData && userId != null) {
            startInitialDataFragment()
        } else {
            startMainFragment()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val setIntent = Intent(Intent.ACTION_MAIN)
        setIntent.addCategory(Intent.CATEGORY_HOME)
        setIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(setIntent)
    }

    private fun checkingFirstLaunch() {
        if (!preferences.getBoolean(FIRST_RUN, false)) {
            editor.putBoolean(FIRST_RUN, true).also {
                it.apply()
            }
            viewModel.presetDataInLocal()
        }
    }

    override fun startMainFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainContainer.id, MainFragment())
            .commit()
    }

    override fun startInitFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainContainer.id, InitialDataFragment())
            .commit()
    }

    private fun startInitialDataFragment() {
        supportFragmentManager.beginTransaction()
            .add(binding.mainContainer.id, InitialDataFragment())
            .commit()
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

    companion object {
        const val TAG = "MainActivity"
        private const val FIRST_RUN = "firstRun"
        const val PREF_IS_FILLED_USER_DATA = "isFilledUserData"
        const val PREF_USER_ID_KEY = "prefUserKey"
        const val PREF_USER_DOCUMENT_ID_KEY = "prefUserDocumentIdKey"
    }
}