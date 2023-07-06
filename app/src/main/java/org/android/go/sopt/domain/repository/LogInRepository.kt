package org.android.go.sopt.domain.repository

import org.android.go.sopt.data.model.response.ResponseLogInDto

interface LogInRepository {
    suspend fun logIn(id: String, password: String):Result<ResponseLogInDto.UserInfo>
}