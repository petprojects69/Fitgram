package ru.petprojects69.fitgram.ui.initData.bottomSheetSex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.BottomSheetSexLayoutBinding
import ru.petprojects69.fitgram.ui.userProfileFragment.UserSex

class BottomSheetSexFragment(
    private var sex: String = UserSex.NOT_DEFINED.sex,
    private val callback: SexCallback
) :
    BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetSexLayoutBinding
    private val viewModel: BottomSheetSexViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetSexLayoutBinding.bind(
            inflater.inflate(
                R.layout.bottom_sheet_sex_layout,
                container
            )
        )
        return binding.root
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.setSex(this.sex)

        binding.manLayout.setOnClickListener {
            clickToButton(UserSex.MAN.sex)
            callback.setMan()
            this.sex = UserSex.MAN.sex
        }

        binding.womanLayout.setOnClickListener {
            clickToButton(UserSex.WOMAN.sex)
            callback.setWoman()
            this.sex = UserSex.WOMAN.sex
        }

        binding.throwOff.setOnClickListener {
            clickToButton(UserSex.NOT_DEFINED.sex)
            callback.setNotDefined()
            this.sex = UserSex.NOT_DEFINED.sex
        }
    }

    private fun renderData(userSex: UserSex) {
        when (userSex) {
            UserSex.MAN -> {
                binding.manLayout.apply {
                    scaleX = 1.13f
                    scaleY = 1.13f
                }
            }

            UserSex.WOMAN -> {
                binding.womanLayout.apply {
                    scaleX = 1.13f
                    scaleY = 1.13f
                }
            }

            UserSex.NOT_DEFINED -> {
                binding.manLayout.apply {
                    scaleX = 1f
                    scaleY = 1f
                }
                binding.womanLayout.apply {
                    scaleX = 1f
                    scaleY = 1f
                }
            }
        }
    }

    private fun clickToButton(sex: String) {
        if (this.sex == sex) return
        when (sex) {
            UserSex.MAN.sex -> {
                selectViewAnimate(binding.manLayout)
                unselectViewAnimate(binding.womanLayout)
            }
            UserSex.WOMAN.sex -> {
                selectViewAnimate(binding.womanLayout)
                unselectViewAnimate(binding.manLayout)
            }
            UserSex.NOT_DEFINED.sex -> {
                startViewAnimate(binding.manLayout)
                startViewAnimate(binding.womanLayout)
            }
        }
    }

    private fun selectViewAnimate(v: View) {
        v.animate()
            .scaleX(1.15f)
            .scaleY(1.15f)
            .start()
    }

    private fun unselectViewAnimate(v: View) {
        v.animate()
            .scaleX(0.85f)
            .scaleY(0.85f)
            .start()
    }

    private fun startViewAnimate(v: View) {
        v.animate()
            .scaleX(1f)
            .scaleY(1f)
            .start()
    }
}

interface SexCallback {
    fun setMan()
    fun setWoman()
    fun setNotDefined()
}