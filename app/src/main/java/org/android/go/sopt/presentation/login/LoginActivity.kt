package org.android.go.sopt.presentation.login


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.presentation.profile.ProfileActivity
import org.android.go.sopt.R
import org.android.go.sopt.data.local.RequestLogInDto
import org.android.go.sopt.data.local.ResponesLogInDto
import org.android.go.sopt.data.local.ServicePool
import org.android.go.sopt.presentation.signup.SignUpActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    //전역변수 설정
    lateinit var binding: ActivityLoginBinding
    private lateinit var activityResult: ActivityResultLauncher<Intent>
    private lateinit var id: String
    private lateinit var pw: String
    private lateinit var name: String
    private lateinit var hobby: String
    private val logInService = ServicePool.logInService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*회원가입 정보 받아오기*/
        setSignUpResult()

        /*회원가입 버튼 눌렀을 때 조건에 맞는 정보를 입력하면 다시 MainActivity로 돌아옴*/
        binding.btSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResult.launch(intent) //다시 돌아옴
        }

        /*키보드 숨기기*/
        binding.root.setOnClickListener {
            hideKeyboard()
        }

        setLogInResult()

        /*로그인 버튼 눌렀을 때*/
        /*binding.btLogIn.setOnClickListener {
            if (binding.etTextInputid.text.toString() == id && binding.etTextInputps.text.toString() == pw) {
                Toast.makeText(this, getString(R.string.loginSuccess), Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("hobby", hobby)
                }

                startActivity(intent)
            } else {
                Toast.makeText(this, getString(R.string.loginFail), Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    private fun hideKeyboard() {
        if (this != null) {
            val imm: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun applyInfoToProfileActivity() {


        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra("name", name)
            putExtra("hobby", hobby)
        }

        startActivity(intent)
    }

    private fun setSignUpResult() {
        activityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                Snackbar.make(
                    binding.root,
                    getString(R.string.signinSuccess),
                    Snackbar.LENGTH_SHORT
                ).show()

                id = result.data?.getStringExtra("id") ?: ""
                pw = result.data?.getStringExtra("pw") ?: ""
                name = result.data?.getStringExtra("name") ?: ""
                hobby = result.data?.getStringExtra("hobby") ?: ""
            }
        }
    }

    private fun setLogInResult() {
        binding.btLogIn.setOnClickListener {
            Log.d("setOnClick", "로그인버튼이 눌렸습니다")
            logInService.logIn(
                with(binding) {
                    Log.d("dfdf","로그인 서비스 시작")
                    RequestLogInDto(
                        etTextInputid.text.toString(),
                        etTextInputps.text.toString()
                    )
                }
            ).enqueue(object : retrofit2.Callback<ResponesLogInDto> {
                override fun onResponse(
                    call: Call<ResponesLogInDto>,
                    response: Response<ResponesLogInDto>
                ) {
                    if (response.isSuccessful) {
                        Log.d("response","로그인 성공함")
                        Toast.makeText(
                            this@LoginActivity,
                            getString(R.string.loginSuccess),
                            Toast.LENGTH_SHORT
                        ).show()
                        applyInfoToProfileActivity()
                        if (!isFinishing) finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            getString(R.string.loginFail),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                override fun onFailure(call: Call<ResponesLogInDto>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "서버통신 실패(응닶값 X)", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}