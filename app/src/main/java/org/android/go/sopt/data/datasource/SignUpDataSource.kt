package org.android.go.sopt.data.datasource

import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto

class SignUpDataSource {
    private val signUpService = ServicePool.signUpService
    suspend fun signUp(requestSignUpDto: RequestSignUpDto):ResponseSignUpDto = signUpService.signUp(requestSignUpDto)
}