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
import androidx.fragment.app.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.request.RequestSignUpDto
import org.android.go.sopt.data.response.ResponseSignUpDto
import org.android.go.sopt.presentation.home.viewmodel.FriendViewModel
import org.android.go.sopt.presentation.signup.viewmodel.SignUpViewModel
import retrofit2.Call
import retrofit2.Response
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

        with(binding) {
            etNewID.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(!signUpId()){
                        tvIdError.text = "아이디는 숫자, 영문 포함 6 ~ 10 글자"
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s != null && s.toString() != "") {
                        Log.e("TAG", "afterTextChanged: $s")
                    }
                }

            })
        }
        with(binding){
            etNewPW.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(!signUpPw()){
                        tvPwError.text = "영문, 숫자, 특수문자 포함 6 ~ 12 글자 "
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    if(s != null && s.toString() != ""){
                        Log.e("TAG","afterTextChanged: $s")
                    }
                }
            })
        }
    }

    /*회원가입 조건*/
    /*아이디 : 영문 숫자 포함 6 - 10 글자 이내 정규식-> ^[A-Za-z0-9]{6,10}$*/
    /*비밀번호 : 영문 숫자 특수문자 포함 6- 12 글자 이내 정규식-> ^.*(?=^.{6,12}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$*/
    private fun signUpId() : Boolean{
        Log.e("Id","아이디 조건")
        val id: String = binding.etNewID.text.toString().trim()
        val idPattern = "^[a-zA-Z0-9]{6,10}\$"

        return if(Pattern.matches(idPattern, id)){
            binding.etNewID.setBackgroundResource(R.drawable.background_correct_status)
            true
        }else{
            binding.etNewID.setBackgroundResource(R.drawable.background_need_change_status)
            false
        }
    }

    private fun signUpPw() : Boolean{
        Log.e("pw","비밀번호 조건")
        val pw: String = binding.etNewPW.text.toString().trim()
        val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&.])[A-Za-z[0-9]\$@\$!%*#?&.]{6,12}\$"

        return if(Pattern.matches(pwPattern,pw)) {
            //올바른 비밀번호일 경우
            binding.etNewPW.setBackgroundResource(R.drawable.background_correct_status)
            true
        } else{
            //올바른 비밀번호가 아닌 경우 테두리에 변화를 준다
            binding.etNewPW.setBackgroundResource(R.drawable.background_need_change_status)
            false
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
                viewModel.loadSignUpData()
                //signUp()
            }
        }
    }
}
