package org.android.go.sopt.presentation.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.data.api.ServicePool.friendService
import org.android.go.sopt.data.response.FriendData
import org.android.go.sopt.data.response.ResponseFriendDto
import org.android.go.sopt.presentation.home.adapter.FriendAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*실제 사용자에게 보여지는 화면*/
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding){}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    fun setAdapter(itemList: List<FriendData?>?){
        Log.e("setAdapter","어댑터 연결 성공")
        val friendAdapter = FriendAdapter()

        binding.rvFriendList.adapter = friendAdapter
        binding.rvFriendList.layoutManager = LinearLayoutManager(requireContext())

        friendAdapter.submitList(itemList)
    }

    private fun setData(){
        friendService.getFriend().enqueue(object: Callback<ResponseFriendDto>{
            override fun onResponse(
                call: Call<ResponseFriendDto>,
                response: Response<ResponseFriendDto>
            ) {
                if(response.isSuccessful){
                    val itemList : List<FriendData>? = response.body()?.data
                    setAdapter(itemList)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}