package ru.petprojects69.fitgram.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
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
        bottomNavigationPanel.setupWithNavController(navigationController)
        checkingFirstLaunch()
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