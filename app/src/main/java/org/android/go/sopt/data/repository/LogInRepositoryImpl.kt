package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.LogInDataSource
import org.android.go.sopt.data.model.request.RequestLogInDto
import org.android.go.sopt.data.model.response.ResponseLogInDto
import org.android.go.sopt.domain.repository.LogInRepository

class LogInRepositoryImpl(private val logInDataSource: LogInDataSource) : LogInRepository {
    override suspend fun LogIn(id: String, password: String): Result<ResponseLogInDto.UserInfo> =
        runCatching {
            logInDataSource.logIn(RequestLogInDto(id, password)).data
        }
}