package org.android.go.sopt.presentation.login.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.home.view.HomeActivity
import org.android.go.sopt.presentation.login.viewmodel.LogInViewModel
import org.android.go.sopt.presentation.profile.view.ProfileActivity
import org.android.go.sopt.presentation.signup.view.SignUpActivity
import org.android.go.sopt.util.UiState
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.showToast
import timber.log.Timber

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    //전역변수 설정
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val viewModel : LogInViewModel by viewModels{ViewModelFactory(this)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addListeners()
        addObservers()
        /*회원가입 정보 받아오기*/
        setSignUpResult()

        /*회원가입 버튼 눌렀을 때 조건에 맞는 정보를 입력하면 다시 MainActivity로 돌아옴*/


        /*키보드 숨기기*/

    }

    private fun addListeners() {
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }


    }

    private fun addObservers() {
        viewModel.logInMessage.observe(this) { message ->
            showToast(message)
        }

        viewModel.logInState.observe(this) { logInState ->
            when (logInState) {
                is UiState.Success -> {

                }

                else -> {
                    Timber.e("오류 발생")
                }
            }
        }
    }

    private fun setSignUpResult() {
        /*회원가입 정보를 저장하는 곳*/

    }

    private fun moveToHome(){
        startActivity(Intent(this,HomeActivity::class.java))
        finish()
    }

   private fun moveToSignUp(){
       resultLauncher.launch(Intent(this,SignUpActivity::class.java))
   }
}