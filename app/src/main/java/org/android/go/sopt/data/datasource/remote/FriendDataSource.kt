package org.android.go.sopt.data.datasource.remote

import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.response.ResponseFriendDto
import org.android.go.sopt.data.service.FriendService

class FriendDataSource {
    private val friendService = ServicePool.friendService
    suspend fun fetchFriendList(): ResponseFriendDto =
        friendService.fetchFriendList()
}