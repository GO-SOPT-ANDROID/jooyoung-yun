package org.android.go.sopt.data.local

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowersService {
    @GET("/api/users?page=2")
    fun getFollowers(
        @Query("page") page:Int
    ): Call<ResponseFollowersDto>
}