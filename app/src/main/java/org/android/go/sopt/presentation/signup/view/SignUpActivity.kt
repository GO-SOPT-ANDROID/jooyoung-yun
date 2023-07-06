package org.android.go.sopt.presentation.signup.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.login.view.LogInActivity
import org.android.go.sopt.presentation.signup.viewmodel.SignUpViewModel
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showSnackBar
import org.android.go.sopt.util.showToast
import timber.log.Timber


/*회원가입 페이지*/
class SignUpActivity : BindingActivity<ActivitySignupBinding>(R.layout.activity_signup) {

    private val viewModel: SignUpViewModel by viewModels{ViewModelFactory(this)}
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
                    viewModel.saveUserInfo()
                    Timber.d("saveUserInfo")
                    /*서버의 정보를 받아오는 뷰모델에서 사용자 이름과 특기만 저장 받는다*/
                    moveToLogIn()
                    Timber.d("moveToLogIn")
                    /*회원가입한 사용자의 정보를 모두 가지고 LogInActivity로 넘어간다*/
                }
                is UiState.Error -> {
                    binding.root.showSnackBar(getString(R.string.sign_up_fail_message))
                }
                else ->{}
            }
        }
    }

    private fun moveToLogIn(){
        val intent = Intent(this, LogInActivity::class.java)
        with(binding){
            intent.putExtra(
                "userInfo",viewModel?.getUserInfo()
            )/*parcelize*/
        }
        setResult(RESULT_OK,intent)
        finish()
    }
}
