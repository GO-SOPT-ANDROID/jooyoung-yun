package org.android.go.sopt

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.databinding.ActivitySignupBinding

/*회원가입 페이지*/
class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSignUpBtn.setOnClickListener{
            if(binding.etNewID.length()<6 || binding.etNewID.length()>10){
                Snackbar.make(binding.root,"아이디는 6~10 사이로 입력해주세요",Snackbar.LENGTH_SHORT).show()
            }
            else if (binding.etNewPW.length()<8 || binding.etNewPW.length()>12){
                Snackbar.make(binding.root,"비밀번호는 8~1 사이로 입력해주세요",Snackbar.LENGTH_SHORT).show()
            }
            else{
                val id = binding.etNewID.text.toString()
                val pw = binding.etNewPW.text.toString()
                val name = binding.etName.text.toString()
                val hobby = binding.etHobby.text.toString()

                //정보들을 전달
                intent.putExtra("id",id)
                intent.putExtra("pw",pw)
                intent.putExtra("name",name)
                intent.putExtra("hobby",hobby)

                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}
