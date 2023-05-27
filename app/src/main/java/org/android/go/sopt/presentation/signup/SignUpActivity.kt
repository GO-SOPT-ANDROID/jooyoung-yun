package org.android.go.sopt.presentation.signup

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.request.RequestSignUpDto
import org.android.go.sopt.data.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response

/*회원가입 페이지*/
class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private val signUpService = ServicePool.signUpService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*키보드 숨기기*/
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        /*서버를 이용한 회원가입*/
        binding.btSignUp.setOnClickListener {
            completeSignUp()
        }
    }

    /*회원가입 조건*/
    private fun signUp() {
        if (binding.etNewID.length() in 6..10 && binding.etNewPW.length() in 8..12) {

            val id = binding.etNewID.text.toString()
            val pw = binding.etNewPW.text.toString()
            val name = binding.etName.text.toString()
            val hobby = binding.etHobby.text.toString()

            //정보들을 전달
            intent.putExtra("id", id)
            intent.putExtra("pw", pw)
            intent.putExtra("name", name)
            intent.putExtra("hobby", hobby)

            setResult(RESULT_OK, intent)
            finish()
        } else if (binding.etNewID.length() < 6 || binding.etNewID.length() > 10) {
            Snackbar.make(binding.root, getString(R.string.id_need), Snackbar.LENGTH_SHORT).show()
        } else if (binding.etNewPW.length() < 8 || binding.etNewPW.length() > 12) {
            Snackbar.make(binding.root, getString(R.string.pw_need), Snackbar.LENGTH_SHORT).show()
        }
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

    private fun canUserSignUp(): Boolean {
        return binding.etNewID.text.length in 6..10
                && binding.etNewPW.text.length in 8..12
                && binding.etName.text.isNotBlank()
                && binding.etHobby.text.isNotBlank()
    }

    private fun completeSignUp() {
        binding.btSignUp.setOnClickListener {
            if (canUserSignUp()) {
                signUpService.signUp(
                    with(binding) {
                        RequestSignUpDto(
                            etNewID.text.toString(),
                            etNewPW.text.toString(),
                            etName.text.toString(),
                            etHobby.text.toString()
                        )
                    }
                ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
                    override fun onResponse(
                        call: Call<ResponseSignUpDto>,
                        response: Response<ResponseSignUpDto>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@SignUpActivity, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT)
                                .show()
                            if (!isFinishing) finish()
                        } else {
                            Toast.makeText(this@SignUpActivity, "서버통신 실패(40X)", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "서버통신 실패(응답값 X)", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
                signUp()
            }
        }
    }
}
