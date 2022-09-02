package ru.petprojects69.fitgram.ui

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ActivityMainBinding
import ru.petprojects69.fitgram.di.PRESET_AEROBIC
import ru.petprojects69.fitgram.di.PRESET_POWER
import ru.petprojects69.fitgram.di.PRESET_TRAINING
import ru.petprojects69.fitgram.domain.entity.Training
import ru.petprojects69.fitgram.domain.entity.exercises.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity
import ru.petprojects69.fitgram.model.database.AppDatabaseDao

private const val FIRST_RUN = "firstRun"

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val bottomNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationView }
    private val navigationController by lazy { findNavController(R.id.navigation_fragment_container) }
    private val scope = CoroutineScope(SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        decorStatusBar()
        checkingFirstLaunch()
        initBottomNavigation()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val setIntent = Intent(Intent.ACTION_MAIN)
        setIntent.addCategory(Intent.CATEGORY_HOME)
        setIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(setIntent)
    }

    private fun initBottomNavigation() {
        binding.labelFragmentTextView.text = resources.getString(R.string.label_timetable)
        bottomNavigationPanel.setupWithNavController(navigationController)
        bottomNavigationPanel.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.timetable_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_timetable)
                    navigationController.navigate(R.id.timetable_item)
                    true
                }
                R.id.training_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_trainings)
                    navigationController.navigate(R.id.training_item)
                    true
                }
                R.id.exercise_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_exercises)
                    navigationController.navigate(R.id.exercise_item)
                    true
                }

                R.id.profile_item -> {
                    binding.labelFragmentTextView.text =
                        resources.getString(R.string.label_profile)
                    navigationController.navigate(R.id.profile_item)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun checkingFirstLaunch() {
        val preferences = getPreferences(MODE_PRIVATE)
        val editor = preferences.edit()

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
        val trainingData: List<Training> by inject(named(PRESET_TRAINING))
        val aerobicExercise: List<AerobicExerciseEntity> by inject(named(PRESET_AEROBIC))
        val powerExercise: List<PowerExerciseEntity> by inject(named(PRESET_POWER))
        dao.presetTraining(trainingData)
        dao.presetAerobicEx(aerobicExercise)
        dao.presetPowerEx(powerExercise)
    }

    private fun decorStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = this.resources.getColor(R.color.color_for_status_bar, null)
    }
}