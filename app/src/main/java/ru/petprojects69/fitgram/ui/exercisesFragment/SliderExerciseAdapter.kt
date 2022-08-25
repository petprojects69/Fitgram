package ru.petprojects69.fitgram.ui.exercisesFragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.petprojects69.fitgram.ui.exercisesFragment.aerobic.AerobicExercisesFragment
import ru.petprojects69.fitgram.ui.exercisesFragment.power.PowerExercisesFragment

class SliderExerciseAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(type: Int): Fragment {
        when (type) {
            ExerciseNumber.POWER.number -> return PowerExercisesFragment.newInstance(type)
            ExerciseNumber.AEROBIC.number -> return AerobicExercisesFragment.newInstance(type)
        }
        return PowerExercisesFragment.newInstance(type)
    }

    override fun getItemCount(): Int {
        return ExerciseNumber.values().size
    }
}

enum class ExerciseNumber(val number: Int) {
    POWER(0),
    AEROBIC(1)
}