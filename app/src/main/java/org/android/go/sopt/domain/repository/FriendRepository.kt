package org.android.go.sopt.domain.repository

import org.android.go.sopt.domain.model.Friend

interface FriendRepository {
    suspend fun fetchFriend() : Result<List<Friend>>
}