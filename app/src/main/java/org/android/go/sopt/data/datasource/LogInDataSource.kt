package org.android.go.sopt.data.datasource

import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.model.request.RequestLogInDto
import org.android.go.sopt.data.model.response.ResponseLogInDto

class LogInDataSource {
    private val logInService = ServicePool.logInService
    suspend fun logIn(requestLogInDto: RequestLogInDto): ResponseLogInDto = logInService.logIn(requestLogInDto)
}