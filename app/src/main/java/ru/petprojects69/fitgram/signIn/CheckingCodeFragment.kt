package ru.petprojects69.fitgram.signIn

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.databinding.FragmentCheckingCodeBinding
import ru.petprojects69.fitgram.ui.utils.millisToSecond

class CheckingCodeFragment : Fragment(R.layout.fragment_checking_code) {

    private val binding: FragmentCheckingCodeBinding by viewBinding()
    private var phoneNumber: String = ""
    private val controller: VerifyCodeController by lazy { activity as VerifyCodeController }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            phoneNumber = it.getString(PHONE_NUMBER_KEY).toString()
        }

        binding.infoTextView.text = resources.getString(R.string.sms_code_info_text, phoneNumber)
        binding.inputCodeEditText.doOnTextChanged { text, _, _, _ ->
            binding.verifyButton.isEnabled = text?.length == 6
        }
        binding.verifyButton.setOnClickListener {
            val code = binding.inputCodeEditText.text.toString()
            controller.verifyPhoneNumberWithCode(code)
        }
        binding.resendButton.setOnClickListener {
            controller.resendVerificationCode(phoneNumber)
        }

        val timer = object : CountDownTimer(TIMER_TIME, TIMER_INTERVAL) {
            override fun onTick(p0: Long) {
                binding.timerTextView.text =
                    resources.getString(R.string.resent_info_text, millisToSecond(p0))
            }

            override fun onFinish() {
                binding.resendButton.isEnabled = true
            }
        }
        timer.start()
    }

    companion object {
        const val PHONE_NUMBER_KEY = "PHONE_NUMBER_KEY"
        private const val TIMER_TIME = 60_000L
        private const val TIMER_INTERVAL = 1_000L
        fun newInstance(bundle: Bundle): CheckingCodeFragment {
            val fragment = CheckingCodeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}