package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.SignUpDataSource
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import org.android.go.sopt.domain.repository.SignUpRepository

class SignUpRepositoryImpl(private val signUpDataSource: SignUpDataSource) : SignUpRepository {
    override suspend fun signUp(
        id: String,
        password: String,
        name: String,
        skill: String
    ): Result<ResponseSignUpDto.UserInfo> =
        runCatching { signUpDataSource.signUp(RequestSignUpDto(id,password,name, skill)).data }
}