package org.android.go.sopt.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import org.android.go.sopt.data.response.FriendData
import org.android.go.sopt.presentation.home.repository.FriendRepository

class FriendViewModel: ViewModel() {
    /*repository 객체 생성*/
    private val friendRepository = FriendRepository()

    /*repository 에 있는 MutableLiveData 를 ViewModel 의 LiveData 에 넣는다*/
    private val friendData : LiveData<List<FriendData?>?>
        get() = friendRepository._friendData

    fun loadFriendData(){
        friendRepository.loadFriendData()
        //repository 에 있는 메서드를 호출하여 서버에서 친구 정보를 가져온다
    }

    fun getAll(): LiveData<List<FriendData?>?> {
        return friendData
    }
}