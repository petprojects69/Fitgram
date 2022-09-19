package ru.petprojects69.fitgram.ui

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.data.database.AppDatabaseDao
import ru.petprojects69.fitgram.databinding.ActivityMainBinding
import ru.petprojects69.fitgram.di.PRESET_EXERCISE
import ru.petprojects69.fitgram.di.PRESET_TRAINING
import ru.petprojects69.fitgram.domain.entity.TrainingEntity

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity
import ru.petprojects69.fitgram.ui.initData.InitialDataFragment
import ru.petprojects69.fitgram.ui.initData.MainActivityController
import kotlin.properties.Delegates

import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity


class MainActivity : AppCompatActivity(), MainActivityController {

    private val binding: ActivityMainBinding by viewBinding()

    private val scope = CoroutineScope(SupervisorJob())
    private var isFilledUserData by Delegates.notNull<Boolean>()
    private var userId: String? = ""
    private val preferences: SharedPreferences by inject()
    private val editor: SharedPreferences.Editor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        decorStatusBar()
        userId = preferences.getString(PREF_USER_ID_KEY, null)
        isFilledUserData = preferences.getBoolean(PREF_IS_FILLED_USER_DATA, false)
        checkingFirstLaunch()
        startFragment()
    }

    private fun startFragment() {
        if (!isFilledUserData && userId != null) {
            startInitialDataFragment()
        } else {
            startMainFragment()
        }
        initBottomNavigation()
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
            scope.launch {
                dataPreset()
            }
        }
    }

    private suspend fun dataPreset() {
        val dao: AppDatabaseDao by inject()
        val trainingData: List<TrainingEntity> by inject(named(PRESET_TRAINING))
        val powerExercise: List<ExerciseEntity> by inject(named(PRESET_EXERCISE))
        dao.presetTraining(trainingData)
        dao.presetEx(powerExercise)
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
        private const val FIRST_RUN = "firstRun"
        const val PREF_IS_FILLED_USER_DATA = "isFilledUserData"
        const val PREF_USER_ID_KEY = "prefUserKey"
        const val PREF_USER_DOCUMENT_ID_KEY = "prefUserDocumentIdKey"
    }
}