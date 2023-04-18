package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.R
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.home.adapter.ProfileAdapter
import org.android.go.sopt.home.adapter.TitleAdapter
import org.android.go.sopt.home.data.Profile
import org.android.go.sopt.home.data.TitleData

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "이렇게 쓰면 안됩니다~" }

    private val mockTitleList = listOf<TitleData>(
        TitleData(
            title_text = "hello world"
        )
    )

    private val mockProfileList = listOf<Profile>(
        Profile(
            image = R.drawable.snoopy, name = "snoopy1", age = "2"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy1", age = "1"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy2", age = "3"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy4", age = "4"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy5", age = "5"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy6", age = "6"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy7", age = "7"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy8", age = "8"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy9", age = "9"
        ), Profile(
            image = R.drawable.snoopy, name = "snoopy10", age = "10"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //대부분의 로직은 여기에 구현된다.
        val profileAdapter = ProfileAdapter(requireContext())
        val titleAdapter = TitleAdapter(requireContext())

        val concatAdapter = ConcatAdapter(titleAdapter, profileAdapter)
        binding.recyclerview.adapter = concatAdapter
        profileAdapter.setProfileList(mockProfileList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}