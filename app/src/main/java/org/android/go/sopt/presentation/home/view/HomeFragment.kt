package org.android.go.sopt.presentation.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.data.api.ServicePool.friendService
import org.android.go.sopt.data.response.FriendData
import org.android.go.sopt.data.response.ResponseFriendDto
import org.android.go.sopt.presentation.home.adapter.FriendAdapter
import org.android.go.sopt.presentation.home.viewmodel.FriendViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/*실제 사용자에게 보여지는 화면*/
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) {}

    private val viewModel: FriendViewModel by viewModels()
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

        /*서버에서 친구 정보를 가져온다*/
        viewModel.loadFriendData()
        viewModel.getAll().observe(viewLifecycleOwner, Observer { data ->
            /*관찰자 안에다가 데이터 변경이 일어날 때 마다 하고 싶은 작업을 적어준다*/
            setAdapter(data)
        })
    }

    private fun setAdapter(itemList: List<FriendData?>?) {
        Log.e("setAdapter", "어댑터 연결 성공")
        val friendAdapter = FriendAdapter()

        binding.rvFriendList.adapter = friendAdapter
        binding.rvFriendList.layoutManager = LinearLayoutManager(requireContext())

        friendAdapter.submitList(itemList)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}