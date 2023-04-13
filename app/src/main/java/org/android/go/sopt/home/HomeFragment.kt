package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.home.adapter.ProfileAdapter
import org.android.go.sopt.home.data.Profile

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding){"이렇게 쓰면 안됩니다~"}

    private val mockProfileList = listOf<Profile>(
        Profile(
            image = R.drawable.snoopy,
            name = "snoopy",
            age = "2"
        ),
        Profile(
            image = R.drawable.snoopy,
            name = "snoopy1",
            age = "1"
        ),
        Profile(
            image = R.drawable.snoopy,
            name = "snoopy2",
            age = "3"
        )
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //대부분의 로직은 여기에 구현된다.
        val adapter = ProfileAdapter(requireContext())
        binding.rvProfile.adapter = adapter
        adapter.setProfileList(mockProfileList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        fun newInstance(): HomeFragment{
            return HomeFragment()
        }
    }
}