package ru.petprojects69.fitgram.ui.exercisesFragment.power

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentPowerExercisesBinding
import ru.petprojects69.fitgram.domain.entity.exercisesEntity.ExerciseEntity
import ru.petprojects69.fitgram.ui.exercisesFragment.SliderExercisesFragmentDirections
import ru.petprojects69.fitgram.ui.exercisesFragment.dialogFragment.OnItemExerciseClickListener

class PowerExercisesFragment : Fragment(R.layout.fragment_power_exercises),
    OnItemExerciseClickListener {

    companion object {
        private const val ARG_COUNT = "powerEx"
        fun newInstance(counter: Int?): PowerExercisesFragment {
            val fragment = PowerExercisesFragment()
            val args = Bundle()
            args.putInt(ARG_COUNT, counter!!)
            fragment.arguments = args
            return fragment
        }
    }

    private val binding: FragmentPowerExercisesBinding by viewBinding()
    private val viewModel: PowerExercisesFragmentViewModel by viewModel()
    private val adapter = PowerExercisesFragmentAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecyclerView.adapter = adapter
        viewModel.allPowerExercise.observe(viewLifecycleOwner) {
            adapter.exercisePowerList = it
        }
    }

    override fun onItemExerciseClick(exercise: ExerciseEntity) {
        val action =
            SliderExercisesFragmentDirections.actionExerciseItemToDetailsExerciseDialogFragment(
                idExercise = exercise.id)
        findNavController().navigate(action)
    }
}