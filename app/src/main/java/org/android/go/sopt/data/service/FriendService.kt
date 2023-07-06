package org.android.go.sopt.data.service

import org.android.go.sopt.data.model.response.ResponseFriendDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET("/api/users")
    suspend fun fetchFriendList(
        @Query("page") page: Int = 2,
    ): ResponseFriendDto
}