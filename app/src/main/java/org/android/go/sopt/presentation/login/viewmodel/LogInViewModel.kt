package org.android.go.sopt.presentation.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.response.ResponseLogInDto
import org.android.go.sopt.domain.repository.LogInRepository
import org.android.go.sopt.util.UiState

class LogInViewModel(private val logInRepository: LogInRepository) : ViewModel() {
    private val _logInResult: MutableLiveData<ResponseLogInDto> = MutableLiveData()
    val logInResult: MutableLiveData<ResponseLogInDto> = _logInResult //전화기 생성

    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val hobby = MutableLiveData("")

    private val _logInState: MutableLiveData<UiState<ResponseLogInDto.UserInfo>> = MutableLiveData()
    val logInState: LiveData<UiState<ResponseLogInDto.UserInfo>> = _logInState

    private val _logInMessage: MutableLiveData<String> = MutableLiveData()
    val logInMessage: MutableLiveData<String> = _logInMessage

    fun logIn() {
        viewModelScope.launch {
            logInRepository.logIn(
                id.value.toString(),
                password.value.toString()
            ).onSuccess { info ->
                _logInState.value = UiState.Success(info)
                _logInMessage.value = "로그인 성공"

            }.onFailure { throwable ->
                _logInState.value = UiState.Error(throwable.message)
                _logInMessage.value = "로그인 실패"
            }
        }
    }
}