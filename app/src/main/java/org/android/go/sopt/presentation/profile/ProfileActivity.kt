package org.android.go.sopt.presentation.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*SignUpActivity에서 MainActivity로 받아온 정보들을 ProfileActivity에서 사용하기*/
        val name = intent.getStringExtra("name")
        val hobby = intent.getStringExtra("hobby")

        binding.tvProfileName.text = "이름 :$name"
        binding.tvProfileHobby.text = "특기 : $hobby"
    }
}