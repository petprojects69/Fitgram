package ru.petprojects69.fitgram.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.ui.main_fragment.TimeTableFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        decorStatusBar()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, TimeTableFragment())
                .commitNow()
        }
    }

    /** метод делает сроку состояния полупрозрачной*/
    private fun decorStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = this.resources.getColor(R.color.color_for_status_bar, null)
    }
}