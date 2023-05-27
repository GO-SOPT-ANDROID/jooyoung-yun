package org.android.go.sopt.data.service

import org.android.go.sopt.data.response.ResponseFriendDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET("/api/users?{page}")
    fun getFriend(
        @Query("page") page:Int
    ): Call<ResponseFriendDto>
}