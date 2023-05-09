package org.android.go.sopt.presentation.signup

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.R
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.data.local.RequestSignUpDto
import org.android.go.sopt.data.local.ResponesSignUpDto
import org.android.go.sopt.data.local.ServicePool
import retrofit2.Call
import retrofit2.Response

/*회원가입 페이지*/
class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private val signUpService = ServicePool.signUpService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        binding.root.setOnClickListener {
            hideKeyboard()
        }

        setContentView(binding.root)

        binding.btSignUpBtn.setOnClickListener {
            signIn()
        }
    }

    /*회원가입 조건*/
    private fun signIn() {
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

    fun hideKeyboard() {
        if (this != null) {
            val imm: InputMethodManager =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                this.currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun canUserSignIn(): Boolean {
        return binding.etNewID.text.length in 6..10
                && binding.etNewPW.text.length in 8..12
                && binding.etName.text.isNotBlank()
                && binding.etHobby.text.isNotBlank()
    }

    private fun completeSignUp() {
        binding.btSignUpBtn.setOnClickListener {
            if (canUserSignIn()) {
                signUpService.signUp(
                    with(binding) {
                        RequestSignUpDto(
                            etNewID.text.toString(),
                            etNewPW.text.toString(),
                            etNewID.text.toString(),
                            etHobby.text.toString()
                        )
                    }
                ).enqueue(object : retrofit2.Callback<ResponesSignUpDto> {
                    override fun onResponse(
                        call: Call<ResponesSignUpDto>,
                        response: Response<ResponesSignUpDto>
                    ) {
                        if (response.isSuccessful) {
                        }
                    }

                    override fun onFailure(call: Call<ResponesSignUpDto>, t: Throwable) {
                    }
                })
            }
        }
    }
}
