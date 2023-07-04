package org.android.go.sopt.presentation.signup.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.signup.viewmodel.SignUpViewModel
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showToast
import timber.log.Timber
import java.util.regex.Pattern


/*회원가입 페이지*/
class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
        addObservers()
    }

    private fun addListeners(){
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
    }

    private fun addObservers(){
        viewModel.signUpMessage.observe(this){ message ->
            showToast(message)
        }

        viewModel.signUpState.observe(this){signUpState->
            when(signUpState){
                is UiState.Success ->{
                    finish()
                }
                else ->{
                    Timber.e(getString(R.string.ui_state_false))
                }
            }
        }
    }
}
