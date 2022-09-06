package ru.petprojects69.fitgram.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.android.ext.android.inject
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentInitialDataBinding
import ru.petprojects69.fitgram.ui.MainActivity.Companion.USER_ID_KEY
import ru.petprojects69.fitgram.ui.utils.showSnack

class InitialDataFragment : Fragment(R.layout.fragment_initial_data) {
    private lateinit var userId: String
    private val binding: FragmentInitialDataBinding by viewBinding()
    private val preferences: SharedPreferences by inject()
    private val editor: SharedPreferences.Editor by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            userId = it.getString(USER_ID_KEY).toString()
        }

        binding.root.showSnack("Hello from InitialFragment, $userId")
    }

    companion object {
        const val INITIAL_FRAGMENT_USER_ID = "initialFragmentUserId"

        fun newInstance(bundle: Bundle): InitialDataFragment {
            val fragment = InitialDataFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}