package org.android.go.sopt.data.service

import org.android.go.sopt.data.request.RequestLogInDto
import org.android.go.sopt.data.response.ResponseLogInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {

    @POST("sign-in")
    fun logIn(
        @Body request: RequestLogInDto
    ): Call<ResponseLogInDto>
}