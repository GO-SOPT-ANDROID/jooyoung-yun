package org.android.go.sopt.data.local

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LogInService {

    @POST("sign-in")
    fun logIn(@Body request: RequestLogInDto
    ):Call<ResponesLogInDto>
}