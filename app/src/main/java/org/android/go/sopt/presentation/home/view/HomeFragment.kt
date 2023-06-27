package org.android.go.sopt.presentation.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.onEach
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.domain.model.Friend
import org.android.go.sopt.presentation.common.ViewModelFactory
import org.android.go.sopt.presentation.home.adapter.FriendAdapter
import org.android.go.sopt.presentation.home.viewmodel.FriendViewModel


/*실제 사용자에게 보여지는 화면*/
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) {}

    private val viewModel: FriendViewModel by viewModels{ViewModelFactory(requireContext())}
    private lateinit var friendAdapter: FriendAdapter
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

        /*서버에서 친구 정보를 가져온다 */
        /*어렵티비..*/
        /*viewModel.friendList.flowWithLifecycle(lifecycle).onEach { friendList ->
            friendAdapter.submitList(friendList.toMutableList())
        }*/
    }

    private fun setAdapter(itemList: List<Friend>) {
        Log.e("setAdapter", "어댑터 연결 성공")
        val friendAdapter = FriendAdapter()

        binding.rvFriendList.adapter = friendAdapter
        binding.rvFriendList.layoutManager = LinearLayoutManager(requireContext())

        //friendAdapter.submitList(itemList.toMutableList())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}