package org.android.go.sopt.presentation.signup.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.presentation.signup.viewmodel.SignUpViewModel
import java.util.regex.Pattern


/*회원가입 페이지*/
class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*키보드 숨기기*/
        binding.root.setOnClickListener {
            hideKeyboard()
        }
        /*서버를 이용한 회원가입*/
        completeSignUp()
    }

    private fun approveSignUpConditions() {
        with(binding) {
            btSignUp.isEnabled = false
            etNewID.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!signUpId()) {
                        tvIdError.text = "아이디는 숫자, 영문 포함 6 ~ 10 글자"
                        Log.e("회원가입 아이디", "경고문 ")
                    }
                    /*조건을 만족하였거나 빈칸일 경우에는 경고 문구 제거*/
                    if (signUpId()) {
                        tvIdError.text = ""
                        etNewID.setBackgroundResource(R.drawable.background_normal_status)
                    }
                    if (etNewID.length() == 0) {
                        tvIdError.text = ""
                        etNewID.setBackgroundResource(R.drawable.background_normal_status)
                        Log.e("회원가입 아이디", "빈 값")
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.toString() != "") {
                        Log.e("TAG", "afterTextChanged: $s")
                        btSignUp.isEnabled = signUpId() /*아이디를 조건에 맞게 설정할 경우 회원가입 버튼 활성화*/
                    }
                }

            })

            etNewPW.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!signUpPw()) {
                        /*조건을 만족시키 못했을 때는 경고 문구가 띄워짐*/
                        tvPwError.text = "영문, 숫자, 특수문자 포함 6 ~ 12 글자 "
                        Log.e("회원가입 비번", "경고문 띄워줌")
                    }
                    /*조건을 만족하였거나 빈칸일 경우에는 경고 문구 제거*/
                    if (signUpPw()) {
                        tvPwError.text = ""
                        etNewPW.setBackgroundResource(R.drawable.background_normal_status)

                    } else if (etNewPW.length() == 0) {
                        tvPwError.text = ""
                        etNewPW.setBackgroundResource(R.drawable.background_normal_status)
                        Log.e("회원가입 비번", "빈 값일 때 ")
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.toString() != "") {
                        Log.e("TAG", "afterTextChanged: $s")
                        btSignUp.isEnabled = signUpPw() /*비밀번호를 조건에 맞도록 설정했을 때 회원가입 버튼 활성화*/
                    }
                }
            })
        }
    }

    private fun signUpId(): Boolean {
        Log.e("Id", "아이디 조건")
        val id: String = binding.etNewID.text.toString().trim()
        val idPattern = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}\$"

        return if (Pattern.matches(idPattern, id)) {
            binding.etNewID.setBackgroundResource(R.drawable.background_correct_status)
            true
        } else {
            binding.etNewID.setBackgroundResource(R.drawable.background_need_change_status)
            false
        }

    }

    private fun signUpPw(): Boolean {
        Log.e("pw", "비밀번호 조건")
        val pw: String = binding.etNewPW.text.toString().trim()
        val pwPattern =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{6,12}\$"

        return if (Pattern.matches(pwPattern, pw)) {
            //올바른 비밀번호일 경우
            binding.etNewPW.setBackgroundResource(R.drawable.background_correct_status)
            true
        } else {
            //올바른 비밀번호가 아닌 경우 테두리에 변화를 준다
            binding.etNewPW.setBackgroundResource(R.drawable.background_need_change_status)
            false
        }
    }

    private fun setSignUpData() {
        val id = binding.etNewID.text.toString()
        val pw = binding.etNewPW.text.toString()
        val name = binding.etName.text.toString()
        val hobby = binding.etHobby.text.toString()

        intent.putExtra("id", id)
        intent.putExtra("pw", pw)
        intent.putExtra("name", name)
        intent.putExtra("hobby", hobby)

        setResult(RESULT_OK, intent)
        finish()
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
        return binding.etName.text.isNotBlank() && binding.etHobby.text.isNotBlank()
    }

    private fun completeSignUp() {
        approveSignUpConditions()

        binding.btSignUp.setOnClickListener {
            if (canUserSignUp()) {
                viewModel.loadSignUpData()
                setSignUpData()
            }
            else{
                Toast.makeText(this,"이름과 특기를 작성해주세요",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
