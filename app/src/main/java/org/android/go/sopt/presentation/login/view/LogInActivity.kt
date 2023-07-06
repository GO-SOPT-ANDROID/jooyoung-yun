package org.android.go.sopt.presentation.login.view


import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.home.view.HomeActivity
import org.android.go.sopt.presentation.login.viewmodel.LogInViewModel
import org.android.go.sopt.presentation.model.UserInfo
import org.android.go.sopt.presentation.signup.view.SignUpActivity
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showSnackBar
import org.android.go.sopt.util.showToast
import timber.log.Timber

class LogInActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    //전역변수 설정
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val viewModel: LogInViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
        addObservers()
        setSignUpResult()

    }

    private fun addListeners() {
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }

        binding.btnSignUp.setOnClickListener{
            Timber.d("회원가입 버튼 눌림")
            moveToSignUp()
        }

    }

    private fun addObservers() {
        viewModel.logInMessage.observe(this) { message ->
            showToast(message)
        }

        viewModel.logInState.observe(this) { logInState ->
            when (logInState) {
                is UiState.Success -> {
                    showToast(getString(R.string.log_in_success_message))
                    moveToHome()
                }

                is UiState.Error -> {
                    showToast(getString(R.string.log_in_fail_message))
                }
                else ->{}
            }
        }
    }

    private fun setSignUpResult() {
        /*회원가입 정보를 저장하는 곳*/
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val resultData = result.data ?:return@registerForActivityResult
                    resultData.getParcelableExtra<UserInfo>("userInfo")?.let{
                        viewModel.setUserInfo(it)
                        binding.root.showSnackBar(getString(R.string.sign_up_success_message))
                    }
                }
            }

    }

    private fun moveToHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun moveToSignUp() {
        resultLauncher.launch(Intent(this, SignUpActivity::class.java))
    }
}