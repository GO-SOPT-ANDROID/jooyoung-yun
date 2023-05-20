package org.android.go.sopt.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import org.android.go.sopt.adapter.FollowerAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.adapter.ProfileAdapter
import org.android.go.sopt.adapter.TitleAdapter
import org.android.go.sopt.data.HomeViewModel
import org.android.go.sopt.data.local.ResponseFollowersDto
import org.android.go.sopt.data.local.ServicePool
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "이렇게 쓰면 안됩니다~" }


    private val followersService = ServicePool.followersService
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var followerList: MutableList<ResponseFollowersDto.RequestFollowersData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        followerAdapter = FollowerAdapter()
        binding.viewpagerHome.adapter = followerAdapter
        viewRecyclerBy(1)
        viewRecyclerBy(2)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun viewRecyclerBy(page: Int){
        followersService.getFollowers(page)
            .enqueue(object : retrofit2.Callback<ResponseFollowersDto> {
                override fun onResponse(
                    call: Call<ResponseFollowersDto>,
                    response: Response<ResponseFollowersDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data.let{
                            followerAdapter.setItemList(followerList)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseFollowersDto>, t: Throwable) {}
            })

    }
}