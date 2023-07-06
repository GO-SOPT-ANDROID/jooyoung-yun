package org.android.go.sopt.presentation.signup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.android.go.sopt.data.datasource.local.GSDataStore
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.data.repository.SignUpRepositoryImpl
import org.android.go.sopt.domain.repository.SignUpRepository
import org.android.go.sopt.presentation.model.UserInfo
import org.android.go.sopt.util.UiState
import timber.log.Timber

class SignUpViewModel(
    private val gsDataStore: GSDataStore,
    private val signUpRepository: SignUpRepository

) : ViewModel() {
    val id = MutableLiveData("")
    val password = MutableLiveData("")
    val name = MutableLiveData("")
    val hobby = MutableLiveData("")

    val isValidId: LiveData<Boolean> = id.map { id ->
        id.matches(Regex(ID_PATTERN))
    }

    val isValidPw: LiveData<Boolean> = id.map { pw ->
        pw.matches(Regex(PASSWORD_PATTERN))
    }

    val isSignUpValid = MediatorLiveData<Boolean>().apply {
        Log.e("isSignUpValid","이것은 하고 있는거니")/*텍스트를 치기 전에 왜 이게 실행되는 거지*/
        addSource(id) { value = checkSignUpCondition() }
        addSource(password) { value = checkSignUpCondition() }
        addSource(name) { value = checkSignUpCondition() }
        addSource(hobby) { value = checkSignUpCondition() }
    }

    private fun checkSignUpCondition(): Boolean {
        Log.e("this","이거하고 있니")/*얘는 잘 실행 중이고*/
        return isValidId.value == true
                && isValidPw.value == true
                && !name.value.isNullOrBlank()
                && !name.value.isNullOrBlank()
    }

    private val _signUpState: MutableLiveData<UiState<ResponseSignUpDto.UserInfo>> =
        MutableLiveData()
    val signUpState: LiveData<UiState<ResponseSignUpDto.UserInfo>> = _signUpState

    private val _signUpMessage: MutableLiveData<String> = MutableLiveData()
    val signUpMessage: LiveData<String> = _signUpMessage

    fun signUp() {
        Log.e("real SignUp","이것은 하고 있는거니")
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
        gsDataStore.userName = name.value.toString()
        gsDataStore.userhobby = hobby.value.toString()

    }

    fun getUserInfo(): UserInfo {
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
            "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"
    }
}