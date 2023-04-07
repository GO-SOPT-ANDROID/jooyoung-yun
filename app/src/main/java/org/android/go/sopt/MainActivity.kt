package org.android.go.sopt


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    //전역변수 설정
    lateinit var binding: ActivityLoginBinding
    private lateinit var activityResult: ActivityResultLauncher<Intent>
    lateinit var id: String
    lateinit var pw: String
    lateinit var name: String
    lateinit var hobby: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setResult()
        /*로그인 성공 시 토스트 메시지*/

        /*회원가입 버튼 눌렀을 때 조건에 맞는 정보를 입력하면 다시 MainActivity로 돌아옴*/
        binding.btSignInBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResult.launch(intent) //다시 돌아옴
        }

        /*로그인 버튼 눌렀을 때*/
        binding.loginBtn.setOnClickListener {
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
        }
    }

    private fun setResult() {
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
}