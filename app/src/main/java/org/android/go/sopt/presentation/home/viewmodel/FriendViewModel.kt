package org.android.go.sopt.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.android.go.sopt.data.repository.FriendRepositoryImpl
import org.android.go.sopt.domain.model.Friend
import timber.log.Timber

class FriendViewModel(val friendRepositoryImpl: FriendRepositoryImpl): ViewModel(){
    private var _friendList = MutableStateFlow<List<Friend>>(listOf())
    val friendList get() = _friendList.asStateFlow()

    init{
        fetchFriendList()
    }

    private fun fetchFriendList() {
        viewModelScope.launch {
            friendRepositoryImpl.fetchFriend()
                .onSuccess { data ->
                    _friendList.value = data
                }
                .onFailure {
                    Timber.d("서버 통신 실패")
                }
        }
    }
}