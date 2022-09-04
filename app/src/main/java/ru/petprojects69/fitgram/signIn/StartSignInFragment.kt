package ru.petprojects69.fitgram.signIn

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentStartSignInBinding
import ru.petprojects69.fitgram.ui.utils.punctuationSymbolNumberPhone

class StartSignInFragment: Fragment(R.layout.fragment_start_sign_in) {
    private val binding: FragmentStartSignInBinding by viewBinding()
    private val controller: StartSignInController by lazy { activity as StartSignInController }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.phoneNumberEditText.doOnTextChanged { text, start, end, _ ->
            binding.getCodeButton.isEnabled = text?.length == 18
            punctuationSymbolNumberPhone(binding.phoneNumberEditText, text, start, end)
        }

        binding.signInGoogleButton.setOnClickListener {
            controller.signIn()
        }

        binding.getCodeButton.setOnClickListener {
            controller.startPhoneNumberVerification(binding.phoneNumberEditText.text.toString())
        }

        binding.signInAnonymousButton.setOnClickListener {
            controller.loginAnonymous()
        }
    }
}