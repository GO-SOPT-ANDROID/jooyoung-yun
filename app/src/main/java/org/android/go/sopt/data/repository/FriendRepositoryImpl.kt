package org.android.go.sopt.data.repository

import org.android.go.sopt.data.datasource.FriendDataSource
import org.android.go.sopt.domain.model.Friend
import org.android.go.sopt.domain.repository.FriendRepository

class FriendRepositoryImpl (private val friendDatasource: FriendDataSource): FriendRepository{
    override suspend fun fetchFriend(): Result<List<Friend>> =
        runCatching{
            friendDatasource.fetchFriendList().toFriend()
        }
}