package org.android.go.sopt.presentation.home.repository

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.data.api.ServicePool
import org.android.go.sopt.data.response.FriendData
import org.android.go.sopt.data.response.ResponseFriendDto
import org.android.go.sopt.presentation.home.adapter.FriendAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FriendRepository {
    /*MutableLiveData 객체 생성*/
    var _friendData = MutableLiveData<List<FriendData?>?>()

    fun loadFriendData(){
        ServicePool.friendService.getFriend().enqueue(object: Callback<ResponseFriendDto> {
            override fun onResponse(
                call: Call<ResponseFriendDto>,
                response: Response<ResponseFriendDto>
            ) {
                if(response.isSuccessful){
                    Log.e("서버 통신 성공",response.message().toString())
                    val itemList : List<FriendData>? = response.body()?.data

                    _friendData.value = itemList
                }
                else{
                    Log.e("error", response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                Log.e("error", t.message.toString())
            }

        })
    }
}