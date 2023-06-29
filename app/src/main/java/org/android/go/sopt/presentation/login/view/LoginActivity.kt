package org.android.go.sopt.presentation.login.view


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.presentation.profile.view.ProfileActivity
import org.android.go.sopt.presentation.signup.view.SignUpActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.presentation.login.viewmodel.LogInViewModel
import org.android.go.sopt.util.binding.BindingActivity
import org.android.go.sopt.util.hideKeyboard

class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    //전역변수 설정
    private lateinit var activityResult: ActivityResultLauncher<Intent>
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String
    private lateinit var hobby: String
    private val viewModel by viewModels<LogInViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*회원가입 정보 받아오기*/
        setSignUpResult()

        /*회원가입 버튼 눌렀을 때 조건에 맞는 정보를 입력하면 다시 MainActivity로 돌아옴*/
        binding.btSignIn.setOnClickListener {
            //쏘기
            viewModel.logIn(
                binding.etTextInputid.text.toString(),
                binding.etTextInputpw.text.toString()
            )
            val intent = Intent(this, SignUpActivity::class.java)
            activityResult.launch(intent) //다시 돌아옴
        }

        viewModel.logInResult.observe(this) {
            startActivity(
                Intent(
                    this@LoginActivity,
                    ProfileActivity::class.java
                ).apply {
                    putExtra("name", name)
                    putExtra("hobby", hobby)
                }
            )
        }
        /*키보드 숨기기*/
        binding.root.setOnClickListener {
            hideKeyboard(it)
        }
    }

    private fun setSignUpResult() {
        activityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                name = result.data?.getStringExtra("name") ?: ""
                hobby = result.data?.getStringExtra("hobby") ?: ""

                Log.d("id 값을 가져오자", id)
            }
        }
    }
}