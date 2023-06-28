package org.android.go.sopt.data.datasource

import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.model.response.ResponseFriendDto

class FriendDataSource {
    private val friendService = ServicePool.friendService
    suspend fun fetchFriendList(): ResponseFriendDto =
        friendService.fetchFriendList()
}