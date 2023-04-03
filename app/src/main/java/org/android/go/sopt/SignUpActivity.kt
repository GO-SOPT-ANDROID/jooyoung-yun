package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*binding.loginBtn.setOnClickListener{
            Snackbar.make(
                binding.root,
                "로그인 되었습니다",
                Snackbar.LENGTH_SHORT
            ).show()
        }*/

        binding.tvSignUpBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java).apply{
                putExtra("str","")
            }
            setResult(RESULT_OK, intent)
            if(!isFinishing) finish()
        }
    }
}