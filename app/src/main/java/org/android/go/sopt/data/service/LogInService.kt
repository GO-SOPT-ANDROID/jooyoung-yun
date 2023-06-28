package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.request.RequestLogInDto
import org.android.go.sopt.data.model.response.ResponseLogInDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {

    @POST("sign-in")
    fun logIn(
        @Body request: RequestLogInDto
    ): Call<ResponseLogInDto>
}