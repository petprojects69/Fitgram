package ru.petprojects69.fitgram.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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
import ru.petprojects69.fitgram.data.database.AppDatabaseDao
import ru.petprojects69.fitgram.databinding.ActivityMainBinding
import ru.petprojects69.fitgram.di.PRESET_AEROBIC
import ru.petprojects69.fitgram.di.PRESET_POWER
import ru.petprojects69.fitgram.di.PRESET_TRAINING
import ru.petprojects69.fitgram.domain.entity.TrainingEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.AerobicExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.PowerExerciseEntity
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val bottomNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationView }
    private val navigationController by lazy { findNavController(R.id.navigation_fragment_container) }
    private val scope = CoroutineScope(SupervisorJob())
    private var isFilledUserData by Delegates.notNull<Boolean>()
    private var userId: String = ""
    private val preferences : SharedPreferences by inject()
    private val editor: SharedPreferences.Editor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        decorStatusBar()
        userId = intent.getStringExtra(USER_ID_KEY).toString()

        isFilledUserData = preferences.getBoolean(IS_FILLED_USER_DATA, false)
        checkingFirstLaunch()

        if (!isFilledUserData) {
            startInitialDataFragment(userId)
        } else {
            Toast.makeText(this, "Hello, $userId", Toast.LENGTH_LONG)
                .show()
            initBottomNavigation()
        }
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
        val aerobicExercise: List<AerobicExerciseEntity> by inject(named(PRESET_AEROBIC))
        val powerExercise: List<PowerExerciseEntity> by inject(named(PRESET_POWER))
        dao.presetTraining(trainingData)
        dao.presetAerobicEx(aerobicExercise)
        dao.presetPowerEx(powerExercise)
    }

    private fun startInitialDataFragment(id: String) {
        val bundle = Bundle()
        bundle.putString(InitialDataFragment.INITIAL_FRAGMENT_USER_ID, id)

        supportFragmentManager.beginTransaction()
            .replace(binding.activityMainContainer.id, InitialDataFragment.newInstance(bundle))
            .commit()
    }

    private fun decorStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = this.resources.getColor(R.color.color_for_status_bar, null)
    }

    companion object {
        private const val FIRST_RUN = "firstRun"
        const val IS_FILLED_USER_DATA = "isFilledUserData"
        const val USER_ID_KEY = "userId"
        const val EMPTY_USER_ID = "empty"
    }
}