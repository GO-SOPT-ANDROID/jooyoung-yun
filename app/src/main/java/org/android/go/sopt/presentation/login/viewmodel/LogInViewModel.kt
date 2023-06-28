package org.android.go.sopt.presentation.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.api.ServicePool.logInService
import org.android.go.sopt.data.model.request.RequestLogInDto
import org.android.go.sopt.data.model.response.ResponseLogInDto
import retrofit2.Call
import retrofit2.Response

class LogInViewModel : ViewModel() {
    private val _logInResult: MutableLiveData<ResponseLogInDto> = MutableLiveData()
    val logInResult: MutableLiveData<ResponseLogInDto> = _logInResult //전화기 생성

    fun logIn(id: String, pw:String){
        logInService.logIn(
            RequestLogInDto(
                id,
                pw
            )
        ).enqueue(object : retrofit2.Callback<ResponseLogInDto> {
            override fun onResponse(
                call: Call<ResponseLogInDto>,
                response: Response<ResponseLogInDto>,
            ){
                if(response.isSuccessful){
                    logInResult.value = response.body()
                }else{
                    //todo
                }
            }
            override fun onFailure(call: Call<ResponseLogInDto>, t: Throwable){
                //todo
            }
        })
    }

}
