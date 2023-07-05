package org.android.go.sopt.presentation.signup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.domain.repository.SignUpRepository
import org.android.go.sopt.presentation.model.UserInfo
import org.android.go.sopt.util.UiState

class SignUpViewModel(private val signUpRepository: SignUpRepository) : ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val hobby = MutableLiveData("")

    val isValidID: LiveData<Boolean> = id.map { id ->
        id.matches(Regex(ID_PATTERN))
    }

    val isVaildPW: LiveData<Boolean> = id.map { pw ->
        pw.matches(Regex(PASSWORD_PATTERN))
    }

    val isSignUpValid = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = checkSignUpCondition() }
        addSource(password) { value = checkSignUpCondition() }
        addSource(name) { value = checkSignUpCondition() }
        addSource(hobby) { value = checkSignUpCondition() }
    }

    private fun checkSignUpCondition(): Boolean {
        return isValidID.value == true && isVaildPW.value == true && !name.value.isNullOrBlank() && !name.value.isNullOrBlank()
    }

    private val _signUpState: MutableLiveData<UiState<ResponseSignUpDto.UserInfo>> =
        MutableLiveData()
    val signUpState: LiveData<UiState<ResponseSignUpDto.UserInfo>> = _signUpState

    private val _signUpMessage: MutableLiveData<String> = MutableLiveData()
    val signUpMessage: LiveData<String> = _signUpMessage

    fun signUp() {
        viewModelScope.launch {
            signUpRepository.signUp(
                id.value.toString(),
                password.value.toString(),
                name.value.toString(),
                hobby.value.toString()
            ).onSuccess { info ->
                _signUpState.value = UiState.Success(info)
                _signUpMessage.value = "회원가입 성공"
            }.onFailure { throwable ->
                _signUpState.value = UiState.Error(throwable.message)
                _signUpMessage.value = "회원가입 중 오류 발생"
            }
        }
    }

    fun saveUserInfo() {

    }

    fun getUserInfo() : UserInfo{
        return UserInfo(
            requireNotNull(id.value),
            requireNotNull(password.value),
            name.value,
            hobby.value
        )
    }

    companion object {
        const val ID_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,10}$"
        const val PASSWORD_PATTERN =
            "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,12}$"
    }
}