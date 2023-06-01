package org.android.go.sopt.presentation.signup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.request.RequestSignUpDto
import org.android.go.sopt.data.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    fun loadSignUpData() {
        ServicePool.signUpService.signUp(
            RequestSignUpDto(
                R.id.et_newID.toString(),
                R.id.et_newPW.toString(),
                R.id.et_name.toString(),
                R.id.et_hobby.toString()
            )
        ).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>
            ) {
                if (response.isSuccessful) {
                    Log.e("서버 통신 성공", response.message().toString())
                } else {
                    Log.e("error", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                Log.e("server error", "서버통신 실패")
            }
        })
    }
}