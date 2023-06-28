package org.android.go.sopt.presentation.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.domain.model.Friend
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.home.adapter.FriendAdapter
import org.android.go.sopt.presentation.home.viewmodel.FriendViewModel
import org.android.go.sopt.util.binding.BindingFragment


/*실제 사용자에게 보여지는 화면*/
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: FriendViewModel by viewModels{ViewModelFactory(requireContext())}
    private lateinit var friendAdapter: FriendAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*서버에서 친구 정보를 가져온다 */
        collectData()
        initLayout()

    }

    private fun initLayout(){
        friendAdapter = FriendAdapter()
        binding.rvFriendList.adapter = friendAdapter
        binding.rvFriendList.layoutManager = LinearLayoutManager(requireContext())

    }
    private fun collectData(){
        viewModel.friendList.flowWithLifecycle(lifecycle).onEach { friendList ->
            friendAdapter.submitList(friendList.toMutableList())
        }.launchIn(lifecycleScope)
    }
}