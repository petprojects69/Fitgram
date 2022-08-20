package ru.petprojects69.fitgram.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val bottomNavigationPanel: BottomNavigationView by lazy { binding.bottomNavigationView }
    private val navigationController by lazy { findNavController(R.id.navigation_fragment_container) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        decorStatusBar()
        bottomNavigationPanel.setupWithNavController(navigationController)
    }

    /** метод делает сроку состояния полупрозрачной*/
    private fun decorStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = this.resources.getColor(R.color.color_for_status_bar, null)
    }
}