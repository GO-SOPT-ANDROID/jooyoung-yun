package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.request.RequestSignUpDto
import org.android.go.sopt.data.model.response.ResponseSignUpDto
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("sign-up")
    suspend fun signUp(
        @Body request: RequestSignUpDto
    ): ResponseSignUpDto
}