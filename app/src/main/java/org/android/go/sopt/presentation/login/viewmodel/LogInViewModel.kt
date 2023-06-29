package org.android.go.sopt.presentation.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.api.ServicePool.logInService
import org.android.go.sopt.data.model.request.RequestLogInDto
import org.android.go.sopt.data.model.response.ResponseLogInDto
import org.android.go.sopt.data.repository.LogInRepositoryImpl
import org.android.go.sopt.domain.repository.LogInRepository
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber
import java.io.PipedWriter

class LogInViewModel(private val logInRepository: LogInRepository) : ViewModel() {
    private val _logInResult: MutableLiveData<ResponseLogInDto> = MutableLiveData()
    val logInResult: MutableLiveData<ResponseLogInDto> = _logInResult //전화기 생성

    fun logIn(id:String,pw:String) =
        viewModelScope.launch {
            val response = logInRepository.LogIn(id,pw)
            if(response.isSuccess){
                Timber.d("LogIn Success")
            }
            else{
                Timber.d("LogIn Failed")
            }
        }

}

