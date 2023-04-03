package org.android.go.sopt

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityLoginBinding
/*1주차 세미나 실습*/
class MainActivity : AppCompatActivity() {
    //전역변수 설정
    lateinit var binding : ActivityLoginBinding
    lateinit var activityResult: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*로그인 성공 시 토스트 메시지*/
        binding.loginBtn.setOnClickListener{
            Toast.makeText(this,"로그인에 성공했습니다.",Toast.LENGTH_SHORT).show()
        }

        activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                //val str = it.data?.getStringExtra("str")?:""
            }
        }

        binding.tvSignInBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            activityResult.launch(intent)
        }
    }
}