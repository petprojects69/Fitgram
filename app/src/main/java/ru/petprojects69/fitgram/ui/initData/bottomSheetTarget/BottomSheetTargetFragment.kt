package ru.petprojects69.fitgram.ui.initData.bottomSheetTarget

import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.BottomSheetTargetLayoutBinding
import ru.petprojects69.fitgram.ui.userProfileFragment.UserTarget

class BottomSheetTargetFragment(
    private val callback: TargetCallback,
    private val currentTarget: String
) : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetTargetLayoutBinding
    private val viewModel: BottomSheetTargetViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetTargetLayoutBinding.bind(
            inflater.inflate(
                R.layout.bottom_sheet_target_layout,
                container
            )
        )
        return binding.root
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.setTarget(currentTarget)

        binding.weightLossButton.setOnClickListener {
            viewModel.setTarget(UserTarget.WEIGHT_LOSS.target)
            callback.weightLoss()
        }

        binding.keepingInShapeButton.setOnClickListener {
            viewModel.setTarget(UserTarget.KEEPING_IN_SHAPE.target)
            callback.keepingInShape()
        }

        binding.massSetButton.setOnClickListener {
            viewModel.setTarget(UserTarget.MASS_SET.target)
            callback.massSet()
        }

        binding.throwOff.setOnClickListener {
            viewModel.setTarget(UserTarget.NOT_DEFINED.target)
            callback.notDefined()
        }
        binding.throwOff.paintFlags = Paint.UNDERLINE_TEXT_FLAG
    }

    private fun renderData(target: UserTarget) {
        when (target) {
            UserTarget.WEIGHT_LOSS -> {
                binding.weightLossButton.apply {
                    setTextColor(resources.getColor(R.color.white, null))
                    setBackgroundColor(
                        resources.getColor(
                            R.color.primaryColor,
                            null
                        )
                    )
                }

                binding.keepingInShapeButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(
                        resources.getColor(
                            R.color.grey_300,
                            null
                        )
                    )
                }

                binding.massSetButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(resources.getColor(R.color.grey_300, null))
                }
            }

            UserTarget.KEEPING_IN_SHAPE -> {
                binding.keepingInShapeButton.apply {
                    setTextColor(resources.getColor(R.color.white, null))
                    setBackgroundColor(
                        resources.getColor(
                            R.color.primaryColor,
                            null
                        )
                    )
                }

                binding.weightLossButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(
                        resources.getColor(
                            R.color.grey_300,
                            null
                        )
                    )
                }

                binding.massSetButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(resources.getColor(R.color.grey_300, null))
                }
            }

            UserTarget.MASS_SET -> {
                binding.massSetButton.apply {
                    setTextColor(resources.getColor(R.color.white, null))
                    setBackgroundColor(
                        resources.getColor(
                            R.color.primaryColor,
                            null
                        )
                    )
                }

                binding.weightLossButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(
                        resources.getColor(
                            R.color.grey_300,
                            null
                        )
                    )
                }

                binding.keepingInShapeButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(resources.getColor(R.color.grey_300, null))
                }
            }

            UserTarget.NOT_DEFINED -> {
                binding.massSetButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(
                        resources.getColor(R.color.grey_300, null)
                    )
                }

                binding.weightLossButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(
                        resources.getColor(R.color.grey_300, null)
                    )
                }

                binding.keepingInShapeButton.apply {
                    setTextColor(resources.getColor(R.color.black, null))
                    setBackgroundColor(resources.getColor(R.color.grey_300, null))
                }
            }
        }
    }
}

interface TargetCallback {
    fun weightLoss()
    fun keepingInShape()
    fun massSet()
    fun notDefined()
}