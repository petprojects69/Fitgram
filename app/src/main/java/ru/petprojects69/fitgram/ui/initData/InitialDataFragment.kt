package ru.petprojects69.fitgram.ui.initData

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentInitialDataBinding
import ru.petprojects69.fitgram.domain.entity.UserEntity
import ru.petprojects69.fitgram.ui.MainActivity
import ru.petprojects69.fitgram.ui.MainActivity.Companion.PREF_USER_DOCUMENT_ID_KEY
import ru.petprojects69.fitgram.ui.MainActivity.Companion.PREF_USER_ID_KEY
import ru.petprojects69.fitgram.ui.utils.showSnack

class InitialDataFragment : Fragment(R.layout.fragment_initial_data) {
    private var userId: String = ""
    private val binding: FragmentInitialDataBinding by viewBinding()
    private val preferences: SharedPreferences by inject()
    private val editor: SharedPreferences.Editor by inject()
    private val viewModel: InitDataViewModel by inject()
    private val controller by lazy { activity as MainActivityController }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = preferences.getString(PREF_USER_ID_KEY, null).toString()
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        binding.avatarImageView.load(R.drawable.man_placeholder) {
            transformations(CircleCropTransformation())
        }

        binding.saveDataButton.setOnClickListener {
            editor.putBoolean(MainActivity.PREF_IS_FILLED_USER_DATA, true).commit()
            val user = UserEntity(
                id = userId,
                name = binding.inputNameEditText.text.toString(),
                sex = binding.sexTabLayout.selectedTabPosition == 0,
                height = binding.inputHeightEditText.text.toString(),
                weight = binding.inputWeightEditText.text.toString()
            )
            viewModel.saveUserData(user)
        }
        binding.sexTabLayout.apply {
            addTab(this.newTab().setText("Man"), 0, true)
            addTab(this.newTab().setText("Woman"), 1, false)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0 -> {
                            binding.avatarImageView.load(R.drawable.man_placeholder) {
                                transformations(CircleCropTransformation())
                            }
                        }
                        1 -> {
                            binding.avatarImageView.load(R.drawable.woman_placeholder) {
                                transformations(CircleCropTransformation())
                            }
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun renderData(state: SaveUserDataState) {
        when (state) {
            is SaveUserDataState.Loading -> {
                binding.progressLayout.visibility = View.VISIBLE
            }
            is SaveUserDataState.Success -> {
                editor.putString(PREF_USER_DOCUMENT_ID_KEY, state.documentId)
                binding.progressLayout.visibility = View.GONE
                binding.root.showSnack("Данные сохранены")
                controller.startMainFragment()
            }
            is SaveUserDataState.Error -> {
                binding.progressLayout.visibility = View.GONE
                binding.root.showSnack(state.e.message.toString())
            }
        }
    }
}