package ru.petprojects69.fitgram.ui.exercisesFragment

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.petprojects69.fitgram.ui.exercisesFragment.aerobic.AerobicExercisesFragment
import ru.petprojects69.fitgram.ui.exercisesFragment.power.PowerExercisesFragment

class SliderExerciseAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    companion object {
        private const val ITEM_COUNT = 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return PowerExercisesFragment.newInstance(position)
            1 -> return AerobicExercisesFragment.newInstance(position)
        }
        return PowerExercisesFragment.newInstance(position)
    }

    override fun getItemCount(): Int {
        return ITEM_COUNT
    }
}